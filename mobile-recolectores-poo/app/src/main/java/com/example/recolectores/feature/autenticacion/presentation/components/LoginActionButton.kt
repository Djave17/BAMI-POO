package com.example.recolectores.feature.autenticacion.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.components.BanButton

/**
 * Acción principal de login
 *
 * Propósito: Mostrar el botón principal del formulario de acceso con su estado de carga.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Reflejar si la acción de ingreso está disponible o en proceso.
 *   - Delegar el clic al contenedor superior sin ejecutar lógica por cuenta propia.
 *   - No acceder a datos ni manejar navegación.
 * 
 */
@Composable
fun LoginActionButton(
    isLoading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BanButton(
        text = if (isLoading) "Ingresando..." else "Ingresar",
        enabled = !isLoading,
        onClick = onClick,
        modifier = modifier
    )
}
