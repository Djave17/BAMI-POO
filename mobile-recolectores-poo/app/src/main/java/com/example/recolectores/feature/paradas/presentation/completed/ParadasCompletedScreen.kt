package com.example.recolectores.feature.paradas.presentation.completed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.feature.paradas.presentation.components.CompletionHeader
import com.example.recolectores.feature.paradas.presentation.components.ParadaFlowScreenScaffold
import com.example.recolectores.feature.paradas.presentation.components.ParadaObservationCard
import com.example.recolectores.feature.paradas.presentation.components.ParadaTopBar
import com.example.recolectores.feature.paradas.presentation.components.ParadasInfoCard

/**
 * Pantalla de cierre visual de la parada.
 */
@Composable
fun ParadasCompletedScreen(
    uiState: ParadasCompletedUiState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ParadaFlowScreenScaffold(
        modifier = modifier,
        topBar = {
            ParadaTopBar(
                title = uiState.paradaTitle,
                statusLabel = uiState.statusLabel,
                statusTone = uiState.statusTone,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            CompletionHeader(message = uiState.footerMessage)
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalBanSpacing.large)
        ) {
            ParadasInfoCard(uiState = uiState.infoCardState)
            ParadaObservationCard(
                text = uiState.observationText,
                placeholder = uiState.observationPlaceholder,
                onValueChange = {},
                isEditable = uiState.isObservationEditable
            )
        }
    }
}
