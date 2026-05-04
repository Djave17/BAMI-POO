package com.example.recolectores.feature.paradas.presentation.detail

import com.example.recolectores.feature.paradas.presentation.components.ParadaStatusTone
import com.example.recolectores.feature.paradas.presentation.components.ParadasInfoCardUiState

/**
 * Estado específico de la pantalla de detalle.
 */
data class ParadasDetailUiState(
    val paradaTitle: String = "",
    val statusLabel: String = "",
    val statusTone: ParadaStatusTone = ParadaStatusTone.Neutral,
    val infoCardState: ParadasInfoCardUiState = ParadasInfoCardUiState(),
    val observationText: String = "",
    val observationPlaceholder: String = "Escribe observaciones o incidencias sobre esta parada...",
    val isObservationEditable: Boolean = true
)
