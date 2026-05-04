package com.example.recolectores.feature.perfil.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.theme.BanDivider
import com.example.recolectores.core.designsystem.theme.BanGrayLight
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanTextSecondary

/**
 * Tarjeta de informacion personal.
 *
 * Cada fila combina un identificador visual a la izquierda y una pareja
 * label/valor a la derecha, tal como se muestra en la referencia.
 */
@Composable
fun PersonalInfoCard(
    nombreCompleto: String,
    correoElectronico: String,
    rol: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = BanDivider.copy(alpha = 0.45f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(
                text = "Información Personal",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                color = BanTextPrimary
            )

            PersonalInfoRow(
                label = "Nombre Completo",
                value = nombreCompleto,
                leading = {
                    InfoCircleIcon(icon = Icons.Outlined.PersonOutline)
                }
            )

            PersonalInfoRow(
                label = "Correo Electrónico",
                value = correoElectronico,
                leading = {
                    InfoCircleIcon(icon = Icons.Outlined.MailOutline)
                }
            )

            PersonalInfoRow(
                label = "Rol",
                value = rol,
                leading = {
                    InfoCircleIcon(icon = Icons.Outlined.WorkOutline)
                }
            )
        }
    }
}

@Composable
private fun PersonalInfoRow(
    label: String,
    value: String,
    leading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        leading()

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = BanTextSecondary
            )
            SelectionContainer {
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Normal,
                    color = BanTextPrimary
                )
            }
        }
    }
}

@Composable
private fun InfoCircleIcon(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(BanGrayLight),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BanTextSecondary,
            modifier = Modifier.size(22.dp)
        )
    }
}
