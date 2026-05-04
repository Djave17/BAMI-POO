package com.example.recolectores.feature.rutas.presentation

import com.example.recolectores.core.designsystem.states.RouteProgressUiState

/**
 * Estado de rutas
 *
 * Propósito: Representar la ruta asignada, su progreso y las paradas visibles para el recolector.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 *
 *   - Ofrecer a Compose un modelo listo para mostrar carga, contenido y errores.
 *   - Centralizar el estado estable de la pantalla de rutas.
 *   - No hablar con infraestructura ni definir datos de prueba.
 *
 */
data class RutasUiState(
    val isLoading: Boolean = false,
    val routeName: String = "",
    val routeCode: String = "",
    val routeProgress: RouteProgressUiState = RouteProgressUiState(),
    val stops: List<RouteStopUiState> = emptyList(),
    val errorMessage: String? = null
) {

}



data class RouteStopUiState(
    val id: String,
    val order: Int,
    val title: String,
    val donorName: String,
    val address: String,
    val estimatedTime: String,
    val status: RouteStopStatusUi
)

enum class RouteStopStatusUi(
    val label: String
) {
    Completed("Completada"),
    WaitingDonation("Esperando Donación"),
    Pending("Pendiente")
}

