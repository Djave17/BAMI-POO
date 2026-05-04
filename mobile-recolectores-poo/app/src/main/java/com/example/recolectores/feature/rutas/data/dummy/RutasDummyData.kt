package com.example.recolectores.feature.rutas.data.dummy

import com.example.recolectores.feature.rutas.presentation.RouteStopStatusUi
import com.example.recolectores.feature.rutas.presentation.RouteStopUiState
import com.example.recolectores.feature.rutas.presentation.RutasUiState

object RutasDummyData {

    val initialState = RutasUiState(
        routeName = "Ruta Zona Norte",
        routeCode = "RN-001",
        stops = listOf(
            RouteStopUiState(
                id = "parada-001",
                order = 1,
                title = "La Canasta - Sucursal Norte",
                donorName = "Supermercado La Canasta",
                address = "Av. Libertador 1245, zona norte",
                estimatedTime = "08:00",
                status = RouteStopStatusUi.Completed
            ),
            RouteStopUiState(
                id = "parada-002",
                order = 2,
                title = "El Trigo Dorado - Central",
                donorName = "Panadería El Trigo Dorado",
                address = "Calle Principal 567, centro",
                estimatedTime = "09:30",
                status = RouteStopStatusUi.WaitingDonation
            ),
            RouteStopUiState(
                id = "parada-003",
                order = 3,
                title = "Mercado Central - Zona 3",
                donorName = "Mercado Central",
                address = "Av. Comercio 890, zona comercial",
                estimatedTime = "11:00",
                status = RouteStopStatusUi.Pending
            )
        )
    )
}
