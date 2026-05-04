package com.example.recolectores.feature.autenticacion.presentation

import com.example.recolectores.MainDispatcherRule
import com.example.recolectores.feature.autenticacion.domain.usecase.ValidarCredencialesLoginUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun createViewModel(): LoginViewModel {
        return LoginViewModel(
            validarCredencialesLoginUseCase = ValidarCredencialesLoginUseCase()
        )
    }

    @Test
    fun inicia_con_el_estado_por_defecto() {
        val viewModel = createViewModel()

        assertEquals(LoginUiState(), viewModel.uiState.value)
    }

    @Test
    fun correo_changed_actualiza_el_correo_y_limpia_el_error() {
        val viewModel = createViewModel()

        viewModel.onEvent(LoginUiEvent.IniciarSesionClicked)
        viewModel.onEvent(LoginUiEvent.CorreoChanged("recolector@ban.org"))

        assertEquals("recolector@ban.org", viewModel.uiState.value.correo)
        assertNull(viewModel.uiState.value.correoError)
    }

    @Test
    fun contrasena_changed_actualiza_la_contrasena_y_limpia_el_error() {
        val viewModel = createViewModel()

        viewModel.onEvent(LoginUiEvent.IniciarSesionClicked)
        viewModel.onEvent(LoginUiEvent.ContrasenaChanged("Segura123"))

        assertEquals("Segura123", viewModel.uiState.value.contrasena)
        assertNull(viewModel.uiState.value.contrasenaError)
    }

    @Test
    fun toggle_mostrar_contrasena_invierte_el_valor_actual() {
        val viewModel = createViewModel()

        viewModel.onEvent(LoginUiEvent.ToggleMostrarContrasena)

        assertTrue(viewModel.uiState.value.mostrarContrasena)
    }

    @Test
    fun recordar_sesion_changed_actualiza_la_bandera() {
        val viewModel = createViewModel()

        viewModel.onEvent(LoginUiEvent.RecordarSesionChanged(false))

        assertEquals(false, viewModel.uiState.value.recordarSesion)
    }

    @Test
    fun iniciar_sesion_invalido_actualiza_errores_y_no_emite_exito() = runTest {
        val viewModel = createViewModel()
        val effects = mutableListOf<LoginEffect>()
        val collectJob = backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.effects.collect(effects::add)
        }

        viewModel.onEvent(LoginUiEvent.IniciarSesionClicked)
        advanceUntilIdle()

        assertEquals("Ingrese su correo", viewModel.uiState.value.correoError)
        assertEquals("Ingrese su contrasena", viewModel.uiState.value.contrasenaError)
        assertTrue(effects.isEmpty())

        collectJob.cancel()
    }

    @Test
    fun iniciar_sesion_valido_emite_login_success() = runTest {
        val viewModel = createViewModel()
        val effects = mutableListOf<LoginEffect>()
        val collectJob = backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.effects.collect(effects::add)
        }

        viewModel.onEvent(LoginUiEvent.CorreoChanged("recolector@ban.org"))
        viewModel.onEvent(LoginUiEvent.ContrasenaChanged("Segura123"))
        viewModel.onEvent(LoginUiEvent.IniciarSesionClicked)
        advanceUntilIdle()

        assertEquals(listOf(LoginEffect.LoginSuccess), effects)

        collectJob.cancel()
    }
}
