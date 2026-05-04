package com.example.recolectores.feature.autenticacion.presentation

/**
 * Estado de login
 *
 * Propósito: Representar toda la información que la pantalla de acceso necesita para dibujarse de forma predecible.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Guardar valores del formulario, errores visibles y estados de carga.
 *   - Servir como fuente única de verdad para `LoginScreen`.
 *   - No contener llamadas a infraestructura ni navegación.
 * 
 */
data class LoginUiState(
    val correo: String = "",
    val contrasena: String = "",
    val recordarSesion: Boolean = true,
    val mostrarContrasena: Boolean = false,
    val correoError: String? = null,
    val contrasenaError: String? = null,
    val isLoading: Boolean = false
)
