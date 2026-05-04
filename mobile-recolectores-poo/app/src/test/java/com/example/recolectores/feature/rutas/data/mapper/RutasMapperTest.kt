package com.example.recolectores.feature.rutas.data.mapper

import com.example.recolectores.feature.paradas.data.dummy.ParadasDummyData
import com.example.recolectores.feature.paradas.data.remote.dto.ParadasDetailDto
import com.example.recolectores.feature.rutas.domain.model.StopStatus
import java.time.Instant
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RutasMapperTest {

    @Test
    fun mapea_los_dummies_de_paradas_a_una_ruta_activa_compartida() {
        val route = RutasMapper.toDomain(
            routeId = "ruta-zona-norte",
            routeName = "Ruta Zona Norte",
            routeCode = "RN-001",
            stops = ParadasDummyData.stopDetails
        )

        assertEquals("ruta-zona-norte", route.id)
        assertEquals("Ruta Zona Norte", route.name)
        assertEquals("RN-001", route.code)
        assertEquals(1, route.progress.completedStops)
        assertEquals(3, route.progress.totalStops)

        val waitingStop = route.stops[1]

        assertEquals("parada-002", waitingStop.id)
        assertEquals(2, waitingStop.order)
        assertTrue(waitingStop.donorName.contains("El Trigo Dorado"))
        assertEquals("El Trigo Dorado - Centro", waitingStop.branchName)
        assertEquals("Calle Principal 567, centro", waitingStop.addressLine)
        assertEquals(Instant.parse("2026-05-01T15:30:00Z"), waitingStop.visitTime)
        assertEquals(StopStatus.WaitingDonation, waitingStop.status)
    }

    @Test
    fun mapea_espera_vencida_como_estado_visible_de_ruta() {
        val route = RutasMapper.toDomain(
            routeId = "ruta-zona-norte",
            routeName = "Ruta Zona Norte",
            routeCode = "RN-001",
            stops = listOf(
                ParadasDetailDto(
                    paradaId = "parada-vencida",
                    paradaNumero = 1,
                    status = "expired_wait",
                    donorName = "Donante Demo",
                    branchName = "Sucursal Demo",
                    addressLine = "Direccion Demo",
                    scheduledArrivalAt = "2026-05-01T14:00:00Z",
                    arrivedAt = "2026-05-01T14:05:00Z"
                )
            )
        )

        assertEquals(StopStatus.ExpiredWait, route.stops.single().status)
    }
}
