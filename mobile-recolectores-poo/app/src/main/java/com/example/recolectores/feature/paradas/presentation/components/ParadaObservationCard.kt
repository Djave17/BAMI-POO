package com.example.recolectores.feature.paradas.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.components.BanSurfaceCard
import com.example.recolectores.core.designsystem.components.BanTextField
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanTextSecondary

/**
 * Tarjeta reutilizable para observaciones.
 *
 * Soporta modo editable y modo solo lectura manteniendo la misma
 * apariencia visual del diseño.
 */
@Composable
fun ParadaObservationCard(
    text: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isEditable: Boolean = true
) {
    val spacing = LocalBanSpacing

    BanSurfaceCard(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.large)
        ) {
            androidx.compose.foundation.layout.Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.small),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.EditNote,
                    contentDescription = null,
                    tint = BanTextSecondary
                )
                Text(
                    text = "Observaciones",
                    style = MaterialTheme.typography.titleLarge,
                    color = BanTextPrimary
                )
            }

            BanTextField(
                value = text,
                onValueChange = onValueChange,
                placeholder = placeholder,
                singleLine = false,
                minLines = 4,
                maxLines = 4,
                readOnly = !isEditable,
                modifier = Modifier.height(100.dp)
            )
        }
    }
}
