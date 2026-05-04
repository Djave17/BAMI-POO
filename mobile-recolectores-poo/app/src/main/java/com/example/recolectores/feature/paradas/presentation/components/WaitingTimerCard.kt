package com.example.recolectores.feature.paradas.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.components.BanSurfaceCard
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanBorder
import com.example.recolectores.core.designsystem.theme.BanOrange
import com.example.recolectores.core.designsystem.theme.BanSurface
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanTextSecondary

/**
 * Tarjeta del temporizador de espera.
 *
 * Recibe el estado ya calculado por dominio para que aquí solo exista
 * responsabilidad visual.
 */
@Composable
fun WaitingTimerCard(
    uiState: WaitingTimerCardUiState,
    modifier: Modifier = Modifier
) {
    val spacing = LocalBanSpacing
    val accentColor = if (uiState.isExpired) BanOrange else com.example.recolectores.core.designsystem.theme.BanGreen

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(1.dp, accentColor),
        colors = CardDefaults.cardColors(
            containerColor = if (uiState.isExpired) BanOrange.copy(alpha = 0.08f) else BanSurface
        )
    ) {
        Column(
            modifier = Modifier.padding(spacing.doubleExtraLarge),
            verticalArrangement = Arrangement.spacedBy(spacing.large)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier
                            .size(42.dp)
                            .background(accentColor, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Timer,
                            contentDescription = null,
                            tint = BanSurface
                        )
                    }

                    Column {
                        Text(
                            text = uiState.title,
                            // El diseño usa una jerarquía más contenida que la default
                            // del tema. Se reduce la escala para que el bloque no compita
                            // visualmente con el header de la parada.
                            style = MaterialTheme.typography.titleMedium,
                            color = accentColor
                        )
                        Text(
                            text = uiState.statusText,
                            style = MaterialTheme.typography.titleLarge,
                            color = accentColor
                        )
                        Text(
                            text = uiState.limitText,
                            style = MaterialTheme.typography.bodyMedium,
                            color = BanTextSecondary
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = uiState.timerText,
                        style = MaterialTheme.typography.titleLarge,
                        color = accentColor
                    )
                    Text(
                        text = uiState.timerCaption,
                        style = MaterialTheme.typography.labelLarge,
                        color = BanTextSecondary
                    )
                }
            }

            LinearProgressIndicator(
                progress = { uiState.progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = accentColor,
                trackColor = accentColor.copy(alpha = 0.18f)
            )

            Text(
                text = uiState.remainingText,
                style = MaterialTheme.typography.labelMedium,
                color = BanTextSecondary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            BanSurfaceCard {
                Text(
                    text = uiState.helperText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (uiState.isExpired) BanOrange else BanTextPrimary
                )
            }
        }
    }
}
