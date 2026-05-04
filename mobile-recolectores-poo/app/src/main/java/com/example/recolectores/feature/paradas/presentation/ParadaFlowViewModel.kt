package com.example.recolectores.feature.paradas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recolectores.feature.donaciones.data.dummy.DonacionesDummyData
import com.example.recolectores.feature.donaciones.domain.model.Donaciones
import com.example.recolectores.feature.donaciones.domain.model.DonacionesItem
import com.example.recolectores.feature.donaciones.domain.usecase.SaveDonationUseCase
import com.example.recolectores.feature.donaciones.domain.usecase.ValidateDonationFormUseCase
import com.example.recolectores.feature.paradas.domain.model.ParadasDetail
import com.example.recolectores.feature.paradas.domain.repository.ParadasRepository
import com.example.recolectores.feature.paradas.domain.usecase.CompleteStopUseCase
import com.example.recolectores.feature.paradas.domain.usecase.ExtendWaitingTimeUseCase
import com.example.recolectores.feature.paradas.domain.usecase.GetStopDetailUseCase
import com.example.recolectores.feature.paradas.domain.usecase.StartWaitingUseCase
import java.time.Clock
import java.time.ZoneId
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * ViewModel compartido por todo el subflujo de la parada.
 *
 * Este es el punto más importante de la arquitectura:
 * - las cuatro rutas leen el mismo estado
 * - observaciones y timer sobreviven entre pantallas
 * - la navegación se expresa como intención, no como dependencia directa
 *
 * El timer se refresca con ticks locales, pero la verdad del negocio
 * sigue viviendo en timestamps del dominio.
 */
class ParadaFlowViewModel(
    private val paradaId: String,
    private val repository: ParadasRepository,
    private val clock: Clock = Clock.systemDefaultZone(),
    private val zoneId: ZoneId = ZoneId.systemDefault()
) : ViewModel() {

    private val categories = DonacionesDummyData.categories
    private val getStopDetailUseCase = GetStopDetailUseCase(repository)
    private val startWaitingUseCase = StartWaitingUseCase(clock)
    private val extendWaitingTimeUseCase = ExtendWaitingTimeUseCase()
    private val completeStopUseCase = CompleteStopUseCase(clock)
    private val validateDonationFormUseCase = ValidateDonationFormUseCase()
    private val saveDonationUseCase = SaveDonationUseCase()

    private val currentTime = MutableStateFlow(clock.instant())
    private val donationItems = MutableStateFlow(
        listOf(createDefaultDonationItem(id = "item-1"))
    )
    private val donationFieldErrors = MutableStateFlow<Map<String, String>>(emptyMap())
    private val pendingNavigation = MutableStateFlow<ParadaFlowDestination?>(null)
    private val pendingExternalAction = MutableStateFlow<ParadaFlowExternalAction?>(null)

    private val sharedInputs = combine(
        getStopDetailUseCase(paradaId),
        currentTime,
        donationItems,
        donationFieldErrors,
        pendingNavigation
    ) { parada, now, items, fieldErrors, navigation ->
        SharedParadaInputs(
            parada = parada,
            now = now,
            items = items,
            fieldErrors = fieldErrors,
            navigation = navigation
        )
    }

    val uiState: StateFlow<ParadaFlowUiState> = combine(
        sharedInputs,
        pendingExternalAction
    ) { sharedInputs, externalAction ->
        val parada = sharedInputs.parada

        if (parada == null) {
            ParadaFlowUiState(
                isLoading = false,
                errorMessage = "No se encontró la parada solicitada.",
                pendingNavigation = sharedInputs.navigation,
                pendingExternalAction = externalAction
            )
        } else {
            ParadaFlowUiState(
                isLoading = false,
                parada = parada,
                observationDraft = parada.observationDraft,
                detailUiState = ParadaFlowUiMapper.toDetailUiState(parada, zoneId),
                waitingUiState = ParadaFlowUiMapper.toWaitingUiState(
                    parada = parada,
                    now = sharedInputs.now,
                    zoneId = zoneId
                ),
                donationUiState = ParadaFlowUiMapper.toDonationUiState(
                    parada = parada,
                    categories = categories,
                    items = sharedInputs.items,
                    fieldErrors = sharedInputs.fieldErrors
                ),
                completedUiState = ParadaFlowUiMapper.toCompletedUiState(parada, zoneId),
                pendingNavigation = sharedInputs.navigation,
                pendingExternalAction = externalAction
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.Eagerly,
        initialValue = ParadaFlowUiState()
    )

    init {
        startTicker()
    }

    fun onObservationChanged(value: String) {
        viewModelScope.launch {
            repository.updateStop(paradaId) { detail ->
                detail.copy(observationDraft = value)
            }
        }
    }

    fun onArrivedAtDestinationClick() {
        viewModelScope.launch {
            // Regla de negocio:
            // la espera inicia solo cuando el recolector confirma llegada.
            // En este punto se registra `arrivedAt`, cambia el status y la
            // UI debe migrar al estado operativo de espera del donante.
            repository.updateStop(paradaId) { detail ->
                startWaitingUseCase(detail)
            }
            pendingNavigation.value = ParadaFlowDestination.Waiting
        }
    }

    fun onRegisterDonationCompletedClick() {
        // La captura de donación solo está permitida una vez iniciada la
        // espera. Por eso esta acción solo emite intención de navegación
        // hacia el formulario y no altera todavía el dominio.
        pendingNavigation.value = ParadaFlowDestination.RegisterDonation
    }

    fun onAddExtraTimeClick() {
        viewModelScope.launch {
            // El tiempo extra se acumula en bloques de 15 minutos.
            // No reinicia el reloj ni reemplaza el límite original.
            // Además, la regla solo permite extender cuando la ventana
            // actual de espera ya fue consumida por completo.
            repository.updateStop(paradaId) { detail ->
                extendWaitingTimeUseCase(
                    detail = detail,
                    now = currentTime.value
                )
            }
        }
    }

    fun onCancelStopClick() {
        pendingExternalAction.value = ParadaFlowExternalAction.CancelStop(paradaId)
    }

    fun onReportIncidentClick() {
        pendingExternalAction.value = ParadaFlowExternalAction.ReportIncident(paradaId)
    }

    fun onDonationCategorySelected(
        itemId: String,
        categoryId: String
    ) {
        updateDonationItem(itemId) { item ->
            item.copy(selectedCategoryId = categoryId)
        }
    }

    fun onDonationWeightChanged(
        itemId: String,
        value: String
    ) {
        updateDonationItem(itemId) { item ->
            item.copy(weightKgInput = value.filterAllowedNumeric())
        }
    }

    fun onDonationValueChanged(
        itemId: String,
        value: String
    ) {
        updateDonationItem(itemId) { item ->
            item.copy(valueInput = value.filterAllowedNumeric())
        }
    }

    fun onAddDonationItemClick() {
        donationItems.update { currentItems ->
            currentItems + createDefaultDonationItem(
                id = "item-${currentItems.size + 1}"
            )
        }
    }

    fun onSaveDonationClick() {
        viewModelScope.launch {
            val parada = repository.getStop(paradaId) ?: return@launch
            val draft = parada.toDonationDraft()
            val validation = validateDonationFormUseCase(draft)

            if (!validation.isValid) {
                donationFieldErrors.value = validation.fieldErrors
                return@launch
            }

            // Solo una donación válida puede completar la parada.
            // El resumen consolidado pasa a dominio y desde ahí la vista
            // final se reconstruye con datos consistentes.
            val summary = saveDonationUseCase(draft)
            repository.updateStop(paradaId) { detail ->
                completeStopUseCase(detail, summary)
            }
            pendingNavigation.value = ParadaFlowDestination.Completed
        }
    }

    fun consumeNavigation() {
        pendingNavigation.value = null
    }

    fun consumeExternalAction() {
        pendingExternalAction.value = null
    }

    private fun startTicker() {
        viewModelScope.launch {
            while (isActive) {
                currentTime.value = clock.instant()
                delay(1_000)
            }
        }
    }

    private fun createDefaultDonationItem(id: String): DonacionesItem {
        return DonacionesItem(
            id = id,
            selectedCategoryId = categories.firstOrNull()?.id,
            weightKgInput = "",
            valueInput = ""
        )
    }

    private fun updateDonationItem(
        itemId: String,
        transform: (DonacionesItem) -> DonacionesItem
    ) {
        donationItems.update { currentItems ->
            currentItems.map { item ->
                if (item.id == itemId) transform(item) else item
            }
        }
        clearErrorsForItem(itemId)
    }

    private fun clearErrorsForItem(itemId: String) {
        donationFieldErrors.update { errors ->
            errors.filterKeys { key -> !key.startsWith("$itemId:") }
        }
    }

    private fun String.filterAllowedNumeric(): String {
        return filterIndexed { index, char ->
            char.isDigit() || (char == '.' && index == indexOf('.'))
        }
    }

    private fun ParadasDetail.toDonationDraft(): Donaciones {
        return Donaciones(
            paradaId = paradaId,
            donorName = donor.name,
            branchName = branch.name,
            items = donationItems.value
        )
    }

    private data class SharedParadaInputs(
        val parada: ParadasDetail?,
        val now: java.time.Instant,
        val items: List<DonacionesItem>,
        val fieldErrors: Map<String, String>,
        val navigation: ParadaFlowDestination?
    )
}
