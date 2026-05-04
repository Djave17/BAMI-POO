package com.example.recolectores.feature.autenticacion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recolectores.feature.autenticacion.domain.usecase.ValidarCredencialesLoginUseCase

/**
 * Fábrica del ViewModel de login
 *
 * Propósito: Construir el `LoginViewModel` con sus dependencias mientras la app aún usa una base inicial sin DI completa.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Crear la instancia correcta del ViewModel para la ruta de login.
 *   - Encapsular la provisión del caso de uso sin mezclar esa lógica dentro del Composable.
 *   - No ejecutar reglas de negocio ni acceder directo a infraestructura.
 * 
 *
 * Nota: Esta fábrica es útil como base inicial. Cuando DI esté completa, la construcción del ViewModel debería moverse a módulos dedicados.
 */
class LoginViewModelFactory(
    private val validarCredencialesLoginUseCase: ValidarCredencialesLoginUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(validarCredencialesLoginUseCase) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
