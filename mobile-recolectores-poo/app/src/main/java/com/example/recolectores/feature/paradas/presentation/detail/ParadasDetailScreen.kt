package com.example.recolectores.feature.paradas.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.feature.paradas.presentation.components.ParadaFlowScreenScaffold
import com.example.recolectores.feature.paradas.presentation.components.ParadaObservationCard
import com.example.recolectores.feature.paradas.presentation.components.ParadaTopBar
import com.example.recolectores.feature.paradas.presentation.components.ParadasActionButtons
import com.example.recolectores.feature.paradas.presentation.components.ParadasInfoCard

/**
 * Pantalla de detalle inicial de una parada pendiente.
 */
@Composable
fun ParadasDetailScreen(
    uiState: ParadasDetailUiState,
    onBackClick: () -> Unit,
    onObservationChange: (String) -> Unit,
    onArrivedClick: () -> Unit,
    onReportIncidentClick: () -> Unit,
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
            ParadasActionButtons(
                onArrivedClick = onArrivedClick,
                onReportIncidentClick = onReportIncidentClick
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalBanSpacing.large)
        ) {
            ParadasInfoCard(uiState = uiState.infoCardState)
            ParadaObservationCard(
                text = uiState.observationText,
                placeholder = uiState.observationPlaceholder,
                onValueChange = onObservationChange,
                isEditable = uiState.isObservationEditable
            )
        }
    }
}
