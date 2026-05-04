package com.example.recolectores.feature.perfil.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recolectores.core.appinfo.BuildConfigAppInfoProvider
import com.example.recolectores.feature.perfil.domain.usecase.LogoutUseCase

/**
 * Punto de entrada de Compose para el feature de perfil.
 *
 * Resuelve dependencias simples con wiring manual para mantener el proyecto
 * consistente con el patron ya existente en otras pantallas.
 */

@Composable
fun PerfilRoute(
    onLogoutCompleted: () -> Unit
) {
    val factory = remember {
        PerfilViewModelFactory(
            appInfoProvider = BuildConfigAppInfoProvider(),
            logoutUseCase = LogoutUseCase()
        )
    }

    val viewModel: PerfilViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsState()

    // Los efectos se traducen aqui a navegacion para mantener el ViewModel libre de Compose Navigation.
    LaunchedEffect(viewModel, onLogoutCompleted) {
        viewModel.effects.collect { effect ->
            when (effect) {
                PerfilEffect.LogoutCompleted -> onLogoutCompleted()
            }
        }
    }

    PerfilScreen(
        uiState = uiState,
        onOpcionClick = { tipo ->
            viewModel.onOpcionClick(tipo)
        },
        onLogoutClick = {
            viewModel.onLogoutClick()
        }
    )
}
