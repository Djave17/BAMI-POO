package com.example.recolectores.feature.paradas.domain.usecase

import com.example.recolectores.feature.paradas.domain.model.DonationSummary
import com.example.recolectores.feature.paradas.domain.model.ParadasDetail
import com.example.recolectores.feature.paradas.domain.model.ParadasStatus
import java.time.Clock

/**
 * Cierra la parada con el resumen consolidado de donación.
 */
class CompleteStopUseCase(
    private val clock: Clock
) {
    operator fun invoke(
        detail: ParadasDetail,
        summary: DonationSummary
    ): ParadasDetail {
        return detail.copy(
            status = ParadasStatus.Completed,
            completedAt = clock.instant(),
            donationSummary = summary,
            canRegisterArrival = false
        )
    }
}
