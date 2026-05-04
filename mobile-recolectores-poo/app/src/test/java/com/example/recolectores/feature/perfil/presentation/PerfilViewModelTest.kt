package com.example.recolectores.feature.perfil.presentation

import com.example.recolectores.MainDispatcherRule
import com.example.recolectores.core.appinfo.AppInfoProvider
import com.example.recolectores.feature.perfil.domain.usecase.LogoutUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class PerfilViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun inicia_con_los_datos_visibles_esperados_para_la_pantalla_de_perfil() {
        val viewModel = PerfilViewModel(
            appInfoProvider = FakeAppInfoProvider(versionName = "2.5.0"),
            logoutUseCase = LogoutUseCase()
        )

        assertEquals("Carlos Mendoza", viewModel.uiState.value.nombreCompleto)
        assertEquals("carlos.mendoza@bami.org", viewModel.uiState.value.correoElectronico)
        assertEquals("Recolector", viewModel.uiState.value.rol)
        assertEquals("BAMI v2.5.0", viewModel.uiState.value.appVersionText)
        assertEquals("© 2026 Banco de Alimentos", viewModel.uiState.value.appOwnerText)
        assertEquals("Acerca de BAMI", viewModel.uiState.value.opciones.last().titulo)
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun cerrar_sesion_ejecuta_limpieza_y_emite_efecto_de_salida() = runTest {
        var sessionCleared = false
        val viewModel = PerfilViewModel(
            appInfoProvider = FakeAppInfoProvider(versionName = "2.5.0"),
            logoutUseCase = LogoutUseCase {
                sessionCleared = true
            }
        )

        val effects = mutableListOf<PerfilEffect>()
        // El collector debe estar activo antes del click porque SharedFlow no reemite eventos pasados.
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.effects.toList(effects)
        }

        viewModel.onLogoutClick()
        advanceUntilIdle()

        assertTrue(sessionCleared)
        assertEquals(listOf(PerfilEffect.LogoutCompleted), effects)
    }

    private class FakeAppInfoProvider(
        private val versionName: String
    ) : AppInfoProvider {
        override fun getVersionName(): String = versionName
    }
}
