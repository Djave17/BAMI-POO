package com.example.recolectores.feature.paradas.presentation.waiting

import com.example.recolectores.feature.paradas.presentation.components.ParadaStatusTone
import com.example.recolectores.feature.paradas.presentation.components.ParadasInfoCardUiState
import com.example.recolectores.feature.paradas.presentation.components.WaitingTimerCardUiState

/**
 * Estado específico de la pantalla de espera del donante.
 */
data class WaitingDonorUiState(
    val paradaTitle: String = "",
    val statusLabel: String = "",
    val statusTone: ParadaStatusTone = ParadaStatusTone.Warning,
    val infoCardState: ParadasInfoCardUiState = ParadasInfoCardUiState(),
    val observationText: String = "",
    val observationPlaceholder: String = "Escribe observaciones o incidencias sobre esta parada...",
    val isObservationEditable: Boolean = true,
    val arrivalTimeText: String = "",
    val remainingMinutes: Int = 0,
    val totalAllowedMinutes: Int = 0,
    val isAddExtraTimeEnabled: Boolean = false,
    val timerCardState: WaitingTimerCardUiState = WaitingTimerCardUiState()
)
