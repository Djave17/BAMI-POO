package com.example.recolectores.feature.paradas.presentation

import com.example.recolectores.feature.donaciones.presentation.DonacionesFormUiState
import com.example.recolectores.feature.paradas.domain.model.ParadasDetail
import com.example.recolectores.feature.paradas.presentation.completed.ParadasCompletedUiState
import com.example.recolectores.feature.paradas.presentation.detail.ParadasDetailUiState
import com.example.recolectores.feature.paradas.presentation.waiting.WaitingDonorUiState

/**
 * Destinos internos del flujo.
 *
 * Se modelan como enum para que el `ViewModel` exprese intención de
 * navegación sin conocer rutas concretas de Compose.
 */
enum class ParadaFlowDestination {
    Detail,
    Waiting,
    RegisterDonation,
    Completed
}

/**
 * Acciones externas que todavía no navegan a una pantalla propia.
 *
 * Se usan como callbacks documentados mientras negocio define su flujo
 * definitivo para incidencias o cancelaciones.
 */
sealed interface ParadaFlowExternalAction {
    data class ReportIncident(val paradaId: String) : ParadaFlowExternalAction
    data class CancelStop(val paradaId: String) : ParadaFlowExternalAction
}

/**
 * Estado maestro del flujo compartido por todas las rutas de la parada.
 */
data class ParadaFlowUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val parada: ParadasDetail? = null,
    val observationDraft: String = "",
    val detailUiState: ParadasDetailUiState = ParadasDetailUiState(),
    val waitingUiState: WaitingDonorUiState = WaitingDonorUiState(),
    val donationUiState: DonacionesFormUiState = DonacionesFormUiState(),
    val completedUiState: ParadasCompletedUiState = ParadasCompletedUiState(),
    val pendingNavigation: ParadaFlowDestination? = null,
    val pendingExternalAction: ParadaFlowExternalAction? = null
)
