package com.example.recolectores.feature.inicio.presentation.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.components.BanStatusChip
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanTextSecondary
import com.example.recolectores.feature.inicio.presentation.NextStopUiState

/**
 * Tarjeta de próxima parada.
 *
 * Muestra la parada que el recolector debe atender dentro de la jornada.
 *
 * Este componente pertenece a la capa presentation.
 *
 * *Funciones:*
 * - El componente detecta el click.
 * - La pantalla envía el evento.
 * - El ViewModel decide qué hacer.
 * - La navegación se maneja fuera del componente.
 *
 * Responsabilidades:
 * - Mostrar el nombre de la parada.
 * - Mostrar el tipo de sucursal o donante.
 * - Mostrar la dirección y la hora estimada.
 * - Mostrar el estado de la parada.
 * - Enviar el click hacia la pantalla, sin manejar navegación directamente.
 */
@Composable
fun NextStopCard(
    nextStop: NextStopUiState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            StopStatusIcon()

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = nextStop.nombre,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleSmall,
                        color = BanTextPrimary,
                        fontWeight = FontWeight.SemiBold
                    )

                    BanStatusChip(
                        text = nextStop.estado
                    )
                }

                Text(
                    text = nextStop.tipo,
                    style = MaterialTheme.typography.bodyMedium,
                    color = BanTextSecondary,
                    fontWeight = FontWeight.SemiBold
                )

                StopInfoRow(
                    icon = Icons.Outlined.LocationOn,
                    text = nextStop.direccion
                )

                StopInfoRow(
                    icon = Icons.Outlined.Schedule,
                    text = nextStop.horaEstimada
                )
            }
        }
    }
}

/**
 * Ícono visual del estado de la parada.
 *
 * Se mantiene privado porque solo forma parte del diseño interno
 * de NextStopCard.
 */
@Composable
private fun StopStatusIcon() {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = BanGreen,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Schedule,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(22.dp)
        )
    }
}



/**
 * Fila de información secundaria.
 *
 * Muestra un ícono junto a un texto corto, como dirección u hora estimada.
 */
@Composable
private fun StopInfoRow(
    icon: ImageVector,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BanTextSecondary,
            modifier = Modifier.size(17.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = BanTextSecondary,
            fontWeight = FontWeight.SemiBold
        )
    }
}