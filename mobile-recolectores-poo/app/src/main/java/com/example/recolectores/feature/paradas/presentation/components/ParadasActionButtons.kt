package com.example.recolectores.feature.paradas.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.components.BanButton
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanOrange
import com.example.recolectores.core.designsystem.theme.BanSurface

/**
 * Acciones del detalle inicial.
 */
@Composable
fun ParadasActionButtons(
    onArrivedClick: () -> Unit,
    onReportIncidentClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(LocalBanSpacing.medium)
    ) {
        BanButton(
            text = "Ya Llegué al Destino",
            onClick = onArrivedClick,
            containerColor = com.example.recolectores.core.designsystem.theme.BanGreen,
            contentColor = BanSurface,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null
                )
            }
        )

        BanButton(
            text = "Reportar incidencia",
            onClick = onReportIncidentClick,
            containerColor = BanOrange,
            contentColor = BanSurface,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = null
                )
            }
        )
    }
}
