package com.example.recolectores.feature.autenticacion.presentation

/**
 * Efectos efímeros de login
 *
 * Propósito: Modelar acciones de una sola vez que no deben quedarse persistidas dentro del `UiState`.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Representar eventos como navegación o mensajes transitorios.
 *   - Evitar que acciones de una sola ejecución contaminen el estado estable de la pantalla.
 *   - No almacenar datos duraderos ni reglas de negocio.
 * 
 */
sealed interface LoginEffect {
    data object LoginSuccess : LoginEffect
}
