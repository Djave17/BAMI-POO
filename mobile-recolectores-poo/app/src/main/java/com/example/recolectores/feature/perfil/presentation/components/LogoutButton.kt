package com.example.recolectores.feature.perfil.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.components.BanOutlinedButton
import com.example.recolectores.core.designsystem.theme.BanDanger

/**
 * Boton reutilizable para cierre de sesion.
 *
 * Encapsular esta accion evita repetir configuracion visual del CTA
 * y deja el contenedor blanco del boton definido en un solo lugar.
 */
@Composable
fun LogoutButton(
    isLoading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BanOutlinedButton(
        text = if (isLoading) "Cerrando..." else "Cerrar Sesión",
        onClick = onClick,
        modifier = modifier,
        enabled = !isLoading,
        textColor = BanDanger
    )
}
