
package com.example.recolectores.feature.inicio.presentation

import androidx.lifecycle.ViewModel
import com.example.recolectores.feature.inicio.data.dummy.InicioDummyData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel de inicio
 *
 * Propósito: Reservar el coordinador de presentación para el resumen de jornada del recolector.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 *
 *   - Recibir eventos de la pantalla, consultar casos de uso y publicar `UiState`.
 *   - Coordinar el flujo entre inicio, solicitudes operativas y estado visible.
 *   - No acceder directo a API, Room, DataStore o WebSocket; este archivo es un placeholder o base inicial.
 *
 *
 * Nota: El flujo previsto es `UiEvent -> ViewModel -> UiState`, dejando los accesos técnicos en domain y data.
 */
class InicioViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(InicioDummyData.initialState)
    val uiState: StateFlow<InicioUiState> = _uiState.asStateFlow()

    fun onEvent(event: InicioUiEvent) {
        when (event) {
            is InicioUiEvent.OnAcceptOperationalRequest -> {
                acceptOperationalRequest(event.requestId)
            }

            is InicioUiEvent.OnDenyOperationalRequest -> {
                denyOperationalRequest(event.requestId)
            }

            InicioUiEvent.OnRefresh -> {
                refreshHome()
            }
        }
    }

    private fun acceptOperationalRequest(requestId: String) {
        _uiState.value = _uiState.value.copy(
            solicitudesOperativas = _uiState.value.solicitudesOperativas
                .filterNot { it.id == requestId }
        )
    }

    private fun denyOperationalRequest(requestId: String) {
        _uiState.value = _uiState.value.copy(
            solicitudesOperativas = _uiState.value.solicitudesOperativas
                .filterNot { it.id == requestId }
        )
    }

    private fun refreshHome() {
        _uiState.value = InicioDummyData.initialState
    }
}