package com.example.recolectores.feature.inicio.presentation

import com.example.recolectores.core.designsystem.states.RouteProgressUiState

/**
 * Estado de la pantalla de inicio.
 *
 * Define los datos que la pantalla Inicio necesita para mostrarse.
 *
 * Este estado no debe contener lógica de negocio ni conectarse directamente
 * con API, Room, DataStore o WebSocket.
 *
 * Los datos reales deben venir desde el ViewModel. Durante la etapa de diseño,
 * el ViewModel puede usar datos fake para probar la interfaz.
 */
data class InicioUiState(
    val isLoading: Boolean = false,
    val recolectorNombre: String = "",
    val recolectorRol: String = "",
    val fechaJornada: String = "",
    val routeProgress: RouteProgressUiState = RouteProgressUiState(),
    val montoEstimadoDonacion: String = "",
    val totalRecolectado: String = "",
    val proximaParada: NextStopUiState? = null,
    val solicitudesOperativas: List<OperationalRequestUiState> = emptyList(),
    val errorMessage: String? = null
)

/**
 * Estado del resumen de la jornada.
 *
 * Guarda los datos principales del día de trabajo del recolector.
 */
data class TodaySummaryUiState(
    val totalRecolectado: String = "",
    val sucursalesRecorridasTexto: String = ""
)

/**
 * Estado de la próxima parada.
 *
 * Guarda la información de la siguiente parada que debe atender el recolector.
 */
data class NextStopUiState(
    val id: String,
    val nombre: String,
    val tipo: String,
    val direccion: String,
    val horaEstimada: String,
    val estado: String
)

/**
 * Estado de una solicitud operativa.
 *
 * Representa una parada sugerida o enviada por Operaciones durante la jornada.
 */
data class OperationalRequestUiState(
    val id: String,
    val nombre: String,
    val tipo: String,
    val direccion: String,
    val horaEstablecida: String
)