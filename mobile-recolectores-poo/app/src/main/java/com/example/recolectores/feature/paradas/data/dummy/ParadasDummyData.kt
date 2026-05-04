package com.example.recolectores.feature.paradas.data.dummy

import com.example.recolectores.feature.paradas.data.remote.dto.DonationSummaryDto
import com.example.recolectores.feature.paradas.data.remote.dto.ParadasDetailDto
import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * Fuente dummy del flujo de paradas.
 *
 * Estrategia:
 * - Los datos visuales replican los diseños.
 * - La hora del demo se controla con un `Clock` propio para que el timer
 *   arranque cerca del estado mostrado en los mockups, pero siga avanzando.
 * - La salida sigue siendo un DTO remoto para ejercitar el mapper real.
 */
object ParadasDummyData {

    val stopDetails: List<ParadasDetailDto> = buildStopDetails(
        date = LocalDate.of(2026, 5, 1),
        zoneId = ZoneId.of("America/Managua")
    )

    fun createDemoClock(zoneId: ZoneId): Clock {
        val baseDateTime = LocalDate.now(zoneId).atTime(10, 13)
        return SessionClock(
            startDateTime = baseDateTime,
            zoneId = zoneId
        )
    }

    fun createSeedStops(zoneId: ZoneId): List<ParadasDetailDto> {
        return buildStopDetails(
            date = LocalDate.now(zoneId),
            zoneId = zoneId
        )
    }

    private fun buildStopDetails(
        date: LocalDate,
        zoneId: ZoneId
    ): List<ParadasDetailDto> {
        return listOf(
            ParadasDetailDto(
                paradaId = "parada-001",
                paradaNumero = 1,
                status = "completed",
                donorName = "Supermercado La Canasta",
                branchName = "La Canasta - Sucursal Norte",
                addressLine = "Av. Libertador 1245, zona norte",
                scheduledArrivalAt = date.atTime(8, 0).toIso(zoneId),
                arrivedAt = date.atTime(8, 5).toIso(zoneId),
                completedAt = date.atTime(8, 20).toIso(zoneId),
                donationSummary = DonationSummaryDto(
                    totalWeightKg = "12.00",
                    totalValue = "12.00",
                    itemCount = 1
                ),
                canRegisterArrival = false
            ),
            ParadasDetailDto(
                paradaId = "parada-002",
                paradaNumero = 2,
                status = "waiting_donation",
                donorName = "Panadería El Trigo Dorado",
                branchName = "El Trigo Dorado - Centro",
                addressLine = "Calle Principal 567, centro",
                scheduledArrivalAt = date.atTime(9, 30).toIso(zoneId),
                arrivedAt = date.atTime(10, 12).toIso(zoneId),
                waitLimitMinutes = 40,
                extraWaitMinutesTotal = 0,
                canRegisterArrival = false
            ),
            ParadasDetailDto(
                paradaId = "parada-003",
                paradaNumero = 3,
                status = "pending",
                donorName = "Mercado Central",
                branchName = "Mercado Central - Zona 3",
                addressLine = "Av. Comercio 890, zona comercial",
                scheduledArrivalAt = date.atTime(11, 0).toIso(zoneId),
                arrivedAt = null,
                waitLimitMinutes = 40,
                extraWaitMinutesTotal = 0,
                canRegisterArrival = true
            )
        ) //n aut 614589
        // 976711
    }

    private fun LocalDateTime.toIso(zoneId: ZoneId): String {
        return atZone(zoneId).toInstant().toString()
    }

    /**
     * Clock de sesión para demo.
     *
     * Arranca en una hora fija del mockup y luego avanza usando tiempo real.
     * Así el timer se ve correcto al abrir la pantalla y sigue corriendo
     * mientras el usuario navega el flujo.
     */
    private class SessionClock(
        startDateTime: LocalDateTime,
        private val zoneId: ZoneId,
        private val bootRealtimeMillis: Long = System.currentTimeMillis()
    ) : Clock() {

        private val startInstant: Instant = startDateTime.atZone(zoneId).toInstant()

        override fun getZone(): ZoneId = zoneId

        override fun withZone(zone: ZoneId): Clock {
            return SessionClock(
                startDateTime = LocalDateTime.ofInstant(startInstant, zone),
                zoneId = zone,
                bootRealtimeMillis = bootRealtimeMillis
            )
        }

        override fun instant(): Instant {
            val elapsedMillis = System.currentTimeMillis() - bootRealtimeMillis
            return startInstant.plusMillis(elapsedMillis)
        }
    }
}
