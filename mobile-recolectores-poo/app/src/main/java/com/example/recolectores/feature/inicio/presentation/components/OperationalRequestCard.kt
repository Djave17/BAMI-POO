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
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.recolectores.core.designsystem.theme.BanDanger
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanTextSecondary
import com.example.recolectores.feature.inicio.presentation.OperationalRequestUiState

/**
 * Tarjeta de solicitud operativa.
 *
 * Muestra una parada enviada por Operaciones durante la jornada del recolector.
 *
 * Este componente pertenece a la capa presentation. Solo pinta una solicitud operativa
 *
 * Responsabilidades:
 * - Mostrar el nombre de la solicitud.
 * - Mostrar el tipo de lugar.
 * - Mostrar la dirección.
 * - Mostrar la hora establecida.
 * - Avisar cuando el usuario acepta la solicitud.
 * - Avisar cuando el usuario deniega la solicitud.
 * - No conectarse directamente a API, Room, DataStore ni WebSocket.
 */
@Composable
fun OperationalRequestCard(
    request: OperationalRequestUiState, // Ese objeto trae los datos que se muestran.
    onAccept: () -> Unit,
    onDeny: () -> Unit, // Eso significa que la tarjeta no sabe qué hacer cuando el usuario toca los botones. Solo avisa.
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                RequestStatusIcon()

                Spacer(modifier = Modifier.width(14.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    Text(
                        text = request.nombre,
                        style = MaterialTheme.typography.titleSmall,
                        color = BanTextPrimary,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = request.tipo,
                        style = MaterialTheme.typography.bodyMedium,
                        color = BanTextSecondary,
                        fontWeight = FontWeight.SemiBold
                    )

                    RequestInfoRow(
                        icon = Icons.Outlined.LocationOn,
                        text = request.direccion
                    )

                    RequestInfoRow(
                        icon = Icons.Outlined.Schedule,
                        text = request.horaEstablecida
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                RequestActionButton(
                    text = "Aceptar",
                    containerColor = BanGreen,
                    onClick = onAccept
                )

                RequestActionButton(
                    text = "Denegar",
                    containerColor = BanDanger,
                    onClick = onDeny
                )
            }
        }
    }
}

/**
 * Ícono de solicitud operativa.
 *
 * Representa visualmente que la solicitud está disponible para revisión.
 */
@Composable
private fun RequestStatusIcon() {
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
            imageVector = Icons.Outlined.History,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(22.dp)
        )
    }
}

/**
 * Fila de información de la solicitud.
 *
 * Muestra un dato secundario con su ícono.
 */
@Composable
private fun RequestInfoRow(
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

/**
 * Botón de acción de la solicitud.
 *
 * Permite reutilizar el mismo diseño para aceptar o denegar.
 */
@Composable
private fun RequestActionButton(
    text: String,
    containerColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}