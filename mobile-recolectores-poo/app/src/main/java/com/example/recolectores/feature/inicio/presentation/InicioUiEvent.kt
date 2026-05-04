
package com.example.recolectores.feature.inicio.presentation


/**
 * Eventos de inicio
 *
 * Propósito: Reservar el contrato de eventos que la pantalla de inicio enviará a su ViewModel.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 *
 *   - Describir interacciones del usuario sobre resumen, avisos o accesos rápidos.
 *   - Permitir un flujo claro desde Compose hacia la lógica de presentación.
 *   - No contener acceso técnico; este archivo es un placeholder o base inicial.
 *
 *
 * Nota: El flujo esperado es `UiEvent -> ViewModel -> UiState` y, si aparece una acción temporal, `Effect`.
 */
sealed interface InicioUiEvent {

    data class OnAcceptOperationalRequest(
        val requestId: String
    ) : InicioUiEvent

    data class OnDenyOperationalRequest(
        val requestId: String
    ) : InicioUiEvent

    data object OnRefresh : InicioUiEvent
}