package com.example.recolectores.feature.perfil.presentation

/**
 * Efectos efimeros de perfil.
 *
 * Modela acciones de una sola vez que no deben permanecer en el `UiState`,
 * como la salida del area autenticada despues de cerrar sesion.
 */
sealed interface PerfilEffect {
    data object LogoutCompleted : PerfilEffect
}
