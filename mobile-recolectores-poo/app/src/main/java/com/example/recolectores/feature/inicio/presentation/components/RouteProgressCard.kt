package com.example.recolectores.feature.inicio.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.components.BanRouteProgressBar
import com.example.recolectores.core.designsystem.states.RouteProgressUiState


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
fun RouteProgressCard(
    progressState: RouteProgressUiState,
    modifier: Modifier = Modifier
) {
    BanRouteProgressBar(
        progressState = progressState,
        modifier = modifier
    )
}
