package com.example.recolectores.feature.paradas.data.mapper

import com.example.recolectores.feature.paradas.data.remote.dto.DonationSummaryDto
import com.example.recolectores.feature.paradas.data.remote.dto.ParadasDetailDto
import com.example.recolectores.feature.paradas.domain.model.Branch
import com.example.recolectores.feature.paradas.domain.model.DonationSummary
import com.example.recolectores.feature.paradas.domain.model.Donor
import com.example.recolectores.feature.paradas.domain.model.ParadasDetail
import com.example.recolectores.feature.paradas.domain.model.ParadasStatus
import java.math.BigDecimal
import java.time.Instant

/**
 * Mapper remoto -> dominio del flujo de paradas.
 *
 * Este archivo documenta la frontera más sensible del feature:
 * - el backend puede cambiar formatos técnicos
 * - el dominio necesita tipos seguros para negocio
 * - la UI nunca debería parsear fechas ni estados por su cuenta
 */
object ParadasMapper {

    fun toDomain(dto: ParadasDetailDto): ParadasDetail {
        return ParadasDetail(
            paradaId = dto.paradaId,
            paradaNumero = dto.paradaNumero,
            status = dto.status.toDomainStatus(),
            donor = Donor(name = dto.donorName),
            branch = Branch(name = dto.branchName),
            addressLine = dto.addressLine,
            scheduledArrivalAt = dto.scheduledArrivalAt.toInstantOrNull(),
            arrivedAt = dto.arrivedAt.toInstantOrNull(),
            waitLimitMinutes = dto.waitLimitMinutes,
            extraWaitMinutesTotal = dto.extraWaitMinutesTotal,
            observationDraft = dto.observationDraft,
            completedAt = dto.completedAt.toInstantOrNull(),
            donationSummary = dto.donationSummary?.toDomain(),
            canReportIncident = dto.canReportIncident,
            canCancelStop = dto.canCancelStop,
            canRegisterArrival = dto.canRegisterArrival
        )
    }

    private fun String.toDomainStatus(): ParadasStatus {
        return when (this) {
            "pending" -> ParadasStatus.Pending
            "waiting_donation" -> ParadasStatus.WaitingDonation
            "completed" -> ParadasStatus.Completed
            "cancelled" -> ParadasStatus.Cancelled
            else -> ParadasStatus.Pending
        }
    }

    private fun String?.toInstantOrNull(): Instant? {
        return this?.let(Instant::parse)
    }

    private fun DonationSummaryDto.toDomain(): DonationSummary {
        return DonationSummary(
            totalWeightKg = totalWeightKg.toBigDecimal(),
            totalValue = totalValue.toBigDecimal(),
            itemCount = itemCount
        )
    }

    private fun String.toBigDecimal(): BigDecimal = BigDecimal(this)
}
