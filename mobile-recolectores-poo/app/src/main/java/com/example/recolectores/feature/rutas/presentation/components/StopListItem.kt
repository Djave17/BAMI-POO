package com.example.recolectores.feature.rutas.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.components.BanStatusChip
import com.example.recolectores.core.designsystem.components.BanStatusChipVariant
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanGrayLight
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanSurface
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanTextSecondary
import com.example.recolectores.feature.rutas.presentation.RouteStopStatusUi
import com.example.recolectores.feature.rutas.presentation.RouteStopUiState

/**
 * Tarjeta individual de parada.
 *
 * Muestra número, nombre, donante, dirección, hora estimada y estado.
 */
@Composable
fun StopListItem(
    stop: RouteStopUiState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalBanSpacing

    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = BanSurface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BanSurface)
                .sizeInMinHeight(),
            verticalAlignment = Alignment.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.large),
                verticalAlignment = Alignment.Top
            ) {
                StopOrderIndicator(
                    order = stop.order,
                    status = stop.status
                )

                Spacer(modifier = Modifier.width(spacing.large))

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(spacing.extraSmall)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(spacing.small)
                    ) {
                        Text(
                            text = stop.title,
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.titleSmall,
                            color = BanTextPrimary,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        BanStatusChip(
                            text = stop.status.label,
                            variant = stop.status.toChipVariant()
                        )
                    }

                    Text(
                        text = stop.donorName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = BanTextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    StopInfoRow(
                        icon = Icons.Outlined.LocationOn,
                        text = stop.address
                    )

                    StopInfoRow(
                        icon = Icons.Outlined.Schedule,
                        text = "Estimado: ${stop.estimatedTime}"
                    )
                }
            }
        }
    }
}

@Composable
private fun StopOrderIndicator(
    order: Int,
    status: RouteStopStatusUi
) {
    val isCompleted = status == RouteStopStatusUi.Completed

    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = if (isCompleted) BanGreen else BanGrayLight,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isCompleted) {
            Icon(
                imageVector = Icons.Outlined.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        } else {
            Text(
                text = order.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = BanGreen,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun StopInfoRow(
    icon: ImageVector,
    text: String
) {
    val spacing = LocalBanSpacing

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BanTextSecondary,
            modifier = Modifier.size(17.dp)
        )

        Spacer(modifier = Modifier.width(spacing.small))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = BanTextSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

private fun RouteStopStatusUi.toChipVariant(): BanStatusChipVariant {
    return when (this) {
        RouteStopStatusUi.Completed -> BanStatusChipVariant.Completed
        RouteStopStatusUi.WaitingDonation -> BanStatusChipVariant.WaitingDonation
        RouteStopStatusUi.Pending -> BanStatusChipVariant.Pending
    }
}

private fun Modifier.sizeInMinHeight(): Modifier {
    return this
}