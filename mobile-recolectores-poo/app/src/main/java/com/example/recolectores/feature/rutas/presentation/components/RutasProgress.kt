package com.example.recolectores.feature.rutas.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.components.BanRouteProgressBar
import com.example.recolectores.core.designsystem.states.RouteProgressUiState


/**
 * Barra de progreso de la ruta.
 *
 * Muestra el avance de paradas completadas.
 */
@Composable
fun RutasProgress(
    progressState: RouteProgressUiState,
    modifier: Modifier = Modifier
) {
    BanRouteProgressBar(
        progressState = progressState,
        modifier = modifier
    )
}
