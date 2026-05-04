package com.example.recolectores.feature.paradas.presentation

import com.example.recolectores.MainDispatcherRule
import com.example.recolectores.feature.paradas.data.dummy.ParadasDummyData
import com.example.recolectores.feature.paradas.data.repository.ParadasRepositoryImpl
import com.example.recolectores.feature.paradas.domain.model.ParadasStatus
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ParadaFlowViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val zoneId = ZoneId.of("America/Managua")
    private val now = Instant.parse("2026-05-01T15:28:00Z")
    private val clock = Clock.fixed(now, zoneId)

    @Test
    fun cuando_registra_llegada_cambia_a_esperando_donacion_y_prepara_la_navegacion() {
        val viewModel = buildViewModel(paradaId = "parada-003")

        viewModel.onObservationChanged("Llamar al guardia al llegar")
        viewModel.onArrivedAtDestinationClick()

        val state = viewModel.uiState.value

        assertEquals(ParadasStatus.WaitingDonation, state.parada?.status)
        assertEquals("09:28", state.waitingUiState.arrivalTimeText)
        assertEquals("Llamar al guardia al llegar", state.observationDraft)
        assertEquals(ParadaFlowDestination.Waiting, state.pendingNavigation)
    }

    @Test
    fun cuando_agrega_tiempo_extra_lo_acumula_en_bloques_validos_de_quince_minutos() {
        val viewModel = buildViewModel(
            paradaId = "parada-002",
            clock = Clock.fixed(
                // La parada dummy llego a 16:12Z; a 16:53Z ya vencio el limite de 40 minutos.
                Instant.parse("2026-05-01T16:53:00Z"),
                zoneId
            )
        )

        viewModel.onAddExtraTimeClick()
        viewModel.onAddExtraTimeClick()

        val state = viewModel.uiState.value

        assertEquals(15, state.parada?.extraWaitMinutesTotal)
        assertEquals(55, state.waitingUiState.totalAllowedMinutes)
        assertFalse(state.waitingUiState.isAddExtraTimeEnabled)
    }

    @Test
    fun cuando_la_espera_aun_no_vence_no_permte_agregar_tiempo_extra() {
        val viewModel = buildViewModel(paradaId = "parada-002")

        viewModel.onAddExtraTimeClick()

        val state = viewModel.uiState.value

        assertEquals(0, state.parada?.extraWaitMinutesTotal)
        assertEquals(40, state.waitingUiState.totalAllowedMinutes)
        assertFalse(state.waitingUiState.isAddExtraTimeEnabled)
    }

    @Test
    fun cuando_pasa_a_registrar_donacion_conserva_las_observaciones_compartidas() {
        val viewModel = buildViewModel(paradaId = "parada-002")

        viewModel.onObservationChanged("El encargado sale en cinco minutos")
        viewModel.onRegisterDonationCompletedClick()

        val state = viewModel.uiState.value

        assertEquals("El encargado sale en cinco minutos", state.observationDraft)
        assertEquals(
            ParadaFlowDestination.RegisterDonation,
            state.pendingNavigation
        )
    }

    @Test
    fun cuando_guarda_la_donacion_marca_la_parada_como_completada_y_calcula_resumen() {
        val viewModel = buildViewModel(paradaId = "parada-002")

        val firstCategoryId = viewModel.uiState.value.donationUiState.categories.first().id

        viewModel.onDonationCategorySelected(itemId = "item-1", categoryId = firstCategoryId)
        viewModel.onDonationWeightChanged(itemId = "item-1", value = "12")
        viewModel.onDonationValueChanged(itemId = "item-1", value = "12")
        viewModel.onSaveDonationClick()

        val state = viewModel.uiState.value

        assertEquals(ParadasStatus.Completed, state.parada?.status)
        assertEquals("12.00 kg", state.completedUiState.totalWeightText)
        assertEquals("$12.00", state.completedUiState.totalValueText)
        assertEquals("1", state.completedUiState.totalItemsText)
        assertEquals(ParadaFlowDestination.Completed, state.pendingNavigation)
        assertNotNull(state.parada?.donationSummary)
    }

    private fun buildViewModel(
        paradaId: String,
        clock: Clock = this.clock
    ): ParadaFlowViewModel {
        return ParadaFlowViewModel(
            paradaId = paradaId,
            repository = ParadasRepositoryImpl(
                seedStops = ParadasDummyData.stopDetails
            ),
            clock = clock,
            zoneId = zoneId
        )
    }
}
