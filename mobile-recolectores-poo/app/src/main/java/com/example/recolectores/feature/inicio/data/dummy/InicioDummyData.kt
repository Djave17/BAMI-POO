package com.example.recolectores.feature.inicio.data.dummy



import com.example.recolectores.core.designsystem.states.RouteProgressUiState
import com.example.recolectores.feature.inicio.presentation.InicioUiState
import com.example.recolectores.feature.inicio.presentation.NextStopUiState
import com.example.recolectores.feature.inicio.presentation.OperationalRequestUiState

/**
 * Datos temporales para construir y revisar la pantalla de inicio.
 *
 * Este archivo existe solo para maquetar la UI mientras todavía no se conecta
 * la pantalla con casos de uso, repositorios, API o base de datos local.
 *
 * No debe contener lógica de negocio.
 */
object InicioDummyData {

    val initialState = InicioUiState(
        recolectorNombre = "Carlos",
        recolectorRol = "Recolector",
        fechaJornada = "Miércoles, 23 de abril de 2026",
        routeProgress = RouteProgressUiState(
            completedStops = 1,
            totalStops = 4,
        ),
        totalRecolectado = "31.5 kg", montoEstimadoDonacion = "3155",

        proximaParada = NextStopUiState(
            id = "parada-la-canasta-norte",
            nombre = "La Canasta - Sucursal Norte",
            tipo = "Supermercado La Canasta",
            direccion = "Av. Libertador 1245, zona norte",
            horaEstimada = "Estimado: 08:00",
            estado = "Pendiente"
        ),
        solicitudesOperativas = listOf(
            OperationalRequestUiState(
                id = "solicitud-walmart",
                nombre = "Walmart",
                tipo = "Supermercado",
                direccion = "Av. Libertador 1245, zona norte",
                horaEstablecida = "Hora establecida: 10:00"
            ),
            OperationalRequestUiState(
                id = "solicitud-la-colonia",
                nombre = "La Colonia",
                tipo = "Supermercado",
                direccion = "Av. Libertador 1245, zona norte",
                horaEstablecida = "Hora establecida: 14:00"
            ),
            OperationalRequestUiState(
                id = "solicitud-la-pali",
                nombre = "Palí El Mayoreo",
                tipo = "Supermercado",
                direccion = "Av. Libertador 1245, zona norte",
                horaEstablecida = "Hora establecida: 18:00"
            )
        )
    )
}