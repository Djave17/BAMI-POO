package com.example.recolectores.feature.rutas.data.mapper

import com.example.recolectores.feature.paradas.data.remote.dto.ParadasDetailDto
import com.example.recolectores.feature.rutas.domain.model.Rutas
import com.example.recolectores.feature.rutas.domain.model.Stop
import com.example.recolectores.feature.rutas.domain.model.StopStatus
import java.time.Instant

/**
 * Mapper hacia el modelo compartido de ruta activa.
 *
 * La fuente actual son DTOs dummy compatibles con backend. Cuando exista API,
 * este mapper debe seguir produciendo el mismo dominio para que inicio, rutas
 * y paradas no dependan de campos tecnicos ni de dummies separados.
 */
object RutasMapper {

    fun toDomain(
        routeId: String,
        routeName: String,
        routeCode: String,
        stops: List<ParadasDetailDto>
    ): Rutas {
        return Rutas(
            id = routeId,
            name = routeName,
            code = routeCode,
            stops = stops
                .sortedBy(ParadasDetailDto::paradaNumero)
                .map { dto -> dto.toDomainStop() }
        )
    }

    private fun ParadasDetailDto.toDomainStop(): Stop {
        return Stop(
            id = paradaId,
            order = paradaNumero,
            donorName = donorName,
            branchName = branchName,
            addressLine = addressLine,
            visitTime = scheduledArrivalAt.toInstantOrNull(),
            status = status.toStopStatus(),
            arrivedAt = arrivedAt.toInstantOrNull(),
            waitLimitMinutes = waitLimitMinutes,
            extraWaitMinutesTotal = extraWaitMinutesTotal
        )
    }

    private fun String.toStopStatus(): StopStatus {
        return when (this) {
            "completed" -> StopStatus.Completed
            "waiting_donation" -> StopStatus.WaitingDonation
            "expired_wait", "espera_vencida" -> StopStatus.ExpiredWait
            else -> StopStatus.Pending
        }
    }

    private fun String?.toInstantOrNull(): Instant? {
        return this?.let(Instant::parse)
    }
}
