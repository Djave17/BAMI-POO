/**
 * ViewModel de detalle de parada
 *
 * Propósito: Reservar el coordinador de presentación del detalle operativo de una parada.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Transformar `UiEvent` en llamadas a casos de uso y publicar `UiState`.
 *   - Coordinar lectura del detalle y acciones sobre la parada actual.
 *   - No acceder directo a API, Room, DataStore o WebSocket; este archivo es un placeholder o base inicial.
 * 
 *
 * Nota: El flujo esperado es `UiEvent -> ViewModel -> UiState` y cualquier acción temporal puede salir como `Effect`.
 */
package com.example.recolectores.feature.paradas.presentation.detail
