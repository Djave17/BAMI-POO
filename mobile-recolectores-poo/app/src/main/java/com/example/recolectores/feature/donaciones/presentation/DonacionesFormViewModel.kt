/**
 * ViewModel de donaciones
 *
 * Propósito: Reservar el coordinador de presentación para captura, validación y guardado de donaciones.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Recibir `UiEvent`, llamar casos de uso y publicar `UiState`.
 *   - Coordinar validaciones, guardado offline y sincronización visible para la UI.
 *   - No acceder directo a API, Room, DataStore o WebSocket; este archivo es un placeholder o base inicial.
 * 
 *
 * Nota: El flujo esperado es `UiEvent -> ViewModel -> UiState` y cualquier acción temporal debería salir como `Effect`.
 */
package com.example.recolectores.feature.donaciones.presentation
