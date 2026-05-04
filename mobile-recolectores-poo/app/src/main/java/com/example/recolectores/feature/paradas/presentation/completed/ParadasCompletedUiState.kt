package com.example.recolectores.feature.paradas.presentation.completed

import com.example.recolectores.feature.paradas.presentation.components.ParadaStatusTone
import com.example.recolectores.feature.paradas.presentation.components.ParadasInfoCardUiState

/**
 * Estado específico de la pantalla de parada completada.
 */
data class ParadasCompletedUiState(
    val paradaTitle: String = "",
    val statusLabel: String = "",
    val statusTone: ParadaStatusTone = ParadaStatusTone.Success,
    val infoCardState: ParadasInfoCardUiState = ParadasInfoCardUiState(),
    val observationText: String = "",
    val observationPlaceholder: String = "Escribe observaciones o incidencias sobre esta parada...",
    val isObservationEditable: Boolean = false,
    val footerMessage: String = "Parada completada exitosamente",
    val totalWeightText: String = "0.00 kg",
    val totalValueText: String = "$0.00",
    val totalItemsText: String = "0"
)
