package com.example.recolectores.feature.paradas.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.components.BanButton
import com.example.recolectores.core.designsystem.components.BanOutlinedButton
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanDanger
import com.example.recolectores.core.designsystem.theme.BanOrange

/**
 * Acciones disponibles mientras la parada espera al donante.
 */
@Composable
fun WaitingActionButtons(
    onRegisterDonationClick: () -> Unit,
    onAddExtraTimeClick: () -> Unit,
    onCancelStopClick: () -> Unit,
    isAddExtraTimeEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(LocalBanSpacing.medium)
    ) {
        BanButton(
            text = "Registrar Donación Completada",
            onClick = onRegisterDonationClick
        )

        BanOutlinedButton(
            text = "Dar 15 Minutos Extra",
            onClick = onAddExtraTimeClick,
            textColor = BanOrange,
            enabled = isAddExtraTimeEnabled
        )

        BanOutlinedButton(
            text = "Cancelar Parada",
            onClick = onCancelStopClick,
            textColor = BanDanger,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.WarningAmber,
                    contentDescription = null
                )
            }
        )
    }
}
