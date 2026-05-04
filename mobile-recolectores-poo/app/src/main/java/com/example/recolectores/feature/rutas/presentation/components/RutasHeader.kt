package com.example.recolectores.feature.rutas.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.components.BanScreenHeader

/**
 * Encabezado de la pantalla de rutas.
 *
 * Recibe el nombre y código de ruta desde el UiState.
 * No define datos quemados.
 */
@Composable
fun RutasHeader(
    routeName: String,
    routeCode: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BanScreenHeader(
        title = routeName,
        subtitle = routeCode,
        modifier = modifier
    )
}