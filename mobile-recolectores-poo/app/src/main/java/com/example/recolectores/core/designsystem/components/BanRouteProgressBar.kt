package com.example.recolectores.core.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.states.RouteProgressUiState
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanProgressTrack
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanTextSecondary


/**
 * Tarjeta de progreso de la ruta.
 *
 * Muestra cuántas paradas han sido completadas durante la jornada.
 *
 * Este componente pertenece a la capa presentation.
 *
 * Responsabilidades:
 * - Mostrar el título de progreso.
 * - Mostrar el texto de avance.
 * - Mostrar una barra de progreso visual.
 * - No modificar el estado de las paradas.
 */

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BanRouteProgressBar(
    progressState: RouteProgressUiState,
    modifier: Modifier = Modifier
) {
    val spacing = LocalBanSpacing

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spacing.small)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Progreso",
                style = MaterialTheme.typography.bodyMedium,
                color = BanTextSecondary
            )

            Text(
                text = progressState.progressText,
                style = MaterialTheme.typography.bodyMedium,
                color = BanTextPrimary,

            )
        }

        LinearProgressIndicator(
            progress = { progressState.progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(50)),
            color = BanGreen,
            trackColor = BanProgressTrack,
            gapSize = 0.dp,
            drawStopIndicator = {}
        )
    }
}
