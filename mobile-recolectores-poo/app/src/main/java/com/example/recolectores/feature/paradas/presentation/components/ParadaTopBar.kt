package com.example.recolectores.feature.paradas.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanTextPrimary

/**
 * Top bar del flujo de parada.
 *
 * Unifica navegación atrás, título y badge de estado entre todas
 * las pantallas del subflujo.
 */
@Composable
fun ParadaTopBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    statusLabel: String? = null,
    statusTone: ParadaStatusTone? = null
) {
    val spacing = LocalBanSpacing

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = spacing.headerHorizontalPadding,
                vertical = spacing.headerVerticalPadding
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.small)
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Volver",
                tint = BanTextPrimary
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = BanTextPrimary
        )

        Spacer(modifier = Modifier.weight(1f))

        if (!statusLabel.isNullOrBlank() && statusTone != null) {
            ParadaStatusBadge(
                label = statusLabel,
                tone = statusTone
            )
        } else {
            Spacer(modifier = Modifier.width(spacing.iconButtonSize))
        }
    }
}
