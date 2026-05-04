package com.example.recolectores.feature.autenticacion.presentation

/**
 * Eventos de login
 *
 * Propósito: Definir las intenciones de usuario que la pantalla envía al `LoginViewModel`.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Describir cambios de texto, toques y acciones del formulario.
 *   - Permitir un flujo claro desde la UI hacia el ViewModel sin acoplar la vista a la lógica.
 *   - No ejecutar trabajo técnico ni reglas de negocio por sí mismo.
 * 
 */
sealed interface LoginUiEvent {
    data class CorreoChanged(val value: String) : LoginUiEvent
    data class ContrasenaChanged(val value: String) : LoginUiEvent
    data class RecordarSesionChanged(val value: Boolean) : LoginUiEvent

    data object ToggleMostrarContrasena : LoginUiEvent
    data object IniciarSesionClicked : LoginUiEvent
    data object OlvideContrasenaClicked : LoginUiEvent
}
