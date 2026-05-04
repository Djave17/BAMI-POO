package com.example.recolectores.feature.paradas.presentation.waiting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.feature.paradas.presentation.components.ParadaFlowScreenScaffold
import com.example.recolectores.feature.paradas.presentation.components.ParadaObservationCard
import com.example.recolectores.feature.paradas.presentation.components.ParadaTopBar
import com.example.recolectores.feature.paradas.presentation.components.ParadasInfoCard
import com.example.recolectores.feature.paradas.presentation.components.WaitingActionButtons
import com.example.recolectores.feature.paradas.presentation.components.WaitingTimerCard

/**
 * Pantalla de espera del donante.
 */
@Composable
fun WaitingDonorScreen(
    uiState: WaitingDonorUiState,
    onBackClick: () -> Unit,
    onObservationChange: (String) -> Unit,
    onRegisterDonationClick: () -> Unit,
    onAddExtraTimeClick: () -> Unit,
    onCancelStopClick: () -> Unit,
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
            WaitingActionButtons(
                onRegisterDonationClick = onRegisterDonationClick,
                onAddExtraTimeClick = onAddExtraTimeClick,
                onCancelStopClick = onCancelStopClick,
                isAddExtraTimeEnabled = uiState.isAddExtraTimeEnabled
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalBanSpacing.large)
        ) {
            ParadasInfoCard(uiState = uiState.infoCardState)
            WaitingTimerCard(uiState = uiState.timerCardState)
            ParadaObservationCard(
                text = uiState.observationText,
                placeholder = uiState.observationPlaceholder,
                onValueChange = onObservationChange,
                isEditable = uiState.isObservationEditable
            )
        }
    }
}
