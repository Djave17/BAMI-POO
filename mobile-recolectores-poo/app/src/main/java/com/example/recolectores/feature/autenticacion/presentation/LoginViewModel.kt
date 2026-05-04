package com.example.recolectores.feature.autenticacion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recolectores.feature.autenticacion.domain.usecase.ValidarCredencialesLoginUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel de login
 *
 * Propósito: Orquestar el flujo entre `LoginUiEvent`, `LoginUiState` y `LoginEffect` para la pantalla de acceso.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Recibir eventos de la UI, aplicar casos de uso y publicar un estado estable para Compose.
 *   - Emitir efectos efímeros cuando la pantalla necesite navegar o ejecutar acciones de una sola vez.
 *   - No acceder directo a API, Room, DataStore o WebSocket.
 * 
 *
 * Nota: El flujo esperado es `UiEvent -> ViewModel -> UiState` para cambios persistentes y `UiEvent -> ViewModel -> Effect` para acciones temporales como navegación.
 */
class LoginViewModel(
    private val validarCredencialesLoginUseCase: ValidarCredencialesLoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // Los efectos resuelven acciones efimeras como navegar sin ensuciar el estado persistente.
    private val _effects = MutableSharedFlow<LoginEffect>()
    val effects: Flow<LoginEffect> = _effects.asSharedFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.CorreoChanged -> {
                _uiState.update { state ->
                    state.copy(
                        correo = event.value,
                        correoError = null
                    )
                }
            }

            is LoginUiEvent.ContrasenaChanged -> {
                _uiState.update { state ->
                    state.copy(
                        contrasena = event.value,
                        contrasenaError = null
                    )
                }
            }

            is LoginUiEvent.RecordarSesionChanged -> {
                _uiState.update { state ->
                    state.copy(recordarSesion = event.value)
                }
            }

            LoginUiEvent.ToggleMostrarContrasena -> {
                _uiState.update { state ->
                    state.copy(mostrarContrasena = !state.mostrarContrasena)
                }
            }

            LoginUiEvent.IniciarSesionClicked -> iniciarSesion()
            LoginUiEvent.OlvideContrasenaClicked -> Unit
        }
    }

    private fun iniciarSesion() {
        val state = _uiState.value
        val result = validarCredencialesLoginUseCase(
            correo = state.correo,
            contrasena = state.contrasena
        )

        _uiState.update { current ->
            current.copy(
                correoError = result.correoError,
                contrasenaError = result.contrasenaError
            )
        }

        if (!result.esValido) {
            return
        }

        viewModelScope.launch {
            _effects.emit(LoginEffect.LoginSuccess)
        }
    }
}
