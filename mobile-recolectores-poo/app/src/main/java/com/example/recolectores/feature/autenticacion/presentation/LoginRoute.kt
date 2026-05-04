package com.example.recolectores.feature.autenticacion.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recolectores.feature.autenticacion.domain.usecase.ValidarCredencialesLoginUseCase

/**
 * Ruta de login
 *
 * Propósito: Conectar el `LoginViewModel` con Compose y traducir efectos efímeros en acciones de navegación.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Crear el ViewModel, observar `UiState` y escuchar `Effect`.
 *   - Entregar a `LoginScreen` solo datos listos para pintar y callbacks de eventos.
 *   - No consultar API, Room, DataStore o WebSocket de forma directa.
 * 
 *
 * Nota: Esta ruta adapta el flujo `UiEvent -> ViewModel -> UiState/Effect` sin meter lógica visual compleja ni lógica de negocio.
 */
@Composable
fun LoginRoute(
    onLoginSuccess: () -> Unit = {}
) {
    val factory = remember {
        LoginViewModelFactory(
            validarCredencialesLoginUseCase = ValidarCredencialesLoginUseCase()
        )
    }
    val viewModel: LoginViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // LoginRoute solo adapta Compose a estado y efectos del ViewModel.
    LaunchedEffect(viewModel, onLoginSuccess) {
        viewModel.effects.collect { effect ->
            when (effect) {
                LoginEffect.LoginSuccess -> onLoginSuccess()
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}
