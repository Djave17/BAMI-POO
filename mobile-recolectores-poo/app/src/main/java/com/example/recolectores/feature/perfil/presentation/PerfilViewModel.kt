package com.example.recolectores.feature.perfil.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recolectores.core.appinfo.AppInfoProvider
import com.example.recolectores.feature.perfil.data.dummy.PerfilDummyData
import com.example.recolectores.feature.perfil.domain.usecase.LogoutUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel de perfil.
 *
 * Consolidar el armado de `PerfilUiState` aqui mantiene a Compose libre
 * de detalles sobre origen de datos y formateo de metadatos visibles.
 */
class PerfilViewModel(
    private val appInfoProvider: AppInfoProvider,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        PerfilDummyData.uiState.copy(
            appVersionText = "BAMI v${appInfoProvider.getVersionName()}",
            appOwnerText = "© 2026 Banco de Alimentos"
        )
    )

    val uiState: StateFlow<PerfilUiState> = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<PerfilEffect>()
    val effects: Flow<PerfilEffect> = _effects.asSharedFlow()

    fun onOpcionClick(tipo: PerfilOpcionTipo) {
        when (tipo) {
            PerfilOpcionTipo.Notificaciones -> {
                // Luego: navegar o mostrar configuración de notificaciones
            }

            PerfilOpcionTipo.AyudaSoporte -> {
                // Luego: abrir ayuda o soporte
            }

            PerfilOpcionTipo.AcercaDeBami -> {
                // Luego: mostrar información de BAMI
            }
        }
    }

    fun onLogoutClick() {
        // Primero limpia la sesion; solo despues emite el efecto para salir del area autenticada.
        viewModelScope.launch {
            logoutUseCase()
            _effects.emit(PerfilEffect.LogoutCompleted)
        }
    }
}
