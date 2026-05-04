package com.example.recolectores.feature.perfil.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recolectores.core.appinfo.AppInfoProvider
import com.example.recolectores.feature.perfil.domain.usecase.LogoutUseCase

/**
 * Factory manual para `PerfilViewModel`.
 *
 * Mantiene la inyeccion explicita del feature sin introducir Hilt
 * solo para resolver la version de la app.
 */
class PerfilViewModelFactory(
    private val appInfoProvider: AppInfoProvider,
    private val logoutUseCase: LogoutUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(PerfilViewModel::class.java)) {
            "PerfilViewModelFactory solo puede crear PerfilViewModel."
        }

        return PerfilViewModel(
            appInfoProvider = appInfoProvider,
            // Inyectado aqui para reemplazar facilmente por limpieza real de token/DataStore.
            logoutUseCase = logoutUseCase
        ) as T
    }
}
