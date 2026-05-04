package com.example.recolectores.feature.paradas.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.components.BanSurfaceCard
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanDivider
import com.example.recolectores.core.designsystem.theme.BanSuccess
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanTextSecondary

/**
 * Tarjeta única para la información principal de la parada.
 */
@Composable
fun ParadasInfoCard(
    uiState: ParadasInfoCardUiState,
    modifier: Modifier = Modifier
) {
    val spacing = LocalBanSpacing

    BanSurfaceCard(modifier = modifier) {
        Text(
            text = uiState.title,
            style = MaterialTheme.typography.titleLarge,
            color = BanTextPrimary
        )

        Spacer(modifier = Modifier.height(spacing.extraLarge))

        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.large)
        ) {
            ParadaInfoTextBlock(
                label = uiState.donorLabel,
                value = uiState.donorName
            )
            ParadaInfoTextBlock(
                label = uiState.branchLabel,
                value = uiState.branchName
            )
            ParadaInfoIconBlock(
                label = uiState.addressLabel,
                value = uiState.addressLine,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = null,
                        tint = BanTextSecondary
                    )
                }
            )
            ParadaInfoIconBlock(
                label = uiState.scheduledArrivalLabel,
                value = uiState.scheduledArrivalTimeText,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.AccessTime,
                        contentDescription = null,
                        tint = BanTextSecondary
                    )
                }
            )

            if (uiState.arrivedAtTimeText != null) {
                HorizontalDivider(color = BanDivider.copy(alpha = 0.8f))
                ParadaInfoTextBlock(
                    label = uiState.arrivedAtLabel,
                    value = uiState.arrivedAtTimeText,
                    valueColor = BanSuccess
                )
            }
        }
    }
}

@Composable
private fun ParadaInfoTextBlock(
    label: String,
    value: String,
    valueColor: androidx.compose.ui.graphics.Color = BanTextPrimary
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = BanTextSecondary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = valueColor
        )
    }
}

@Composable
private fun ParadaInfoIconBlock(
    label: String,
    value: String,
    icon: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        icon()
        Spacer(modifier = Modifier.width(LocalBanSpacing.small))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = BanTextSecondary
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = BanTextPrimary
            )
        }
    }
}
