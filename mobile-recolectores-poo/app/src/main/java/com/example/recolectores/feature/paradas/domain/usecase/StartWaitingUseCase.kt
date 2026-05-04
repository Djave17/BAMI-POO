package com.example.recolectores.feature.paradas.domain.usecase

import com.example.recolectores.feature.paradas.domain.model.ParadasDetail
import com.example.recolectores.feature.paradas.domain.model.ParadasStatus
import java.time.Clock

/**
 * Inicia la ventana de espera cuando el recolector confirma llegada.
 *
 * La marca de tiempo se toma desde `Clock` inyectado para que la regla
 * sea determinística en pruebas y no dependa del reloj global.
 */
class StartWaitingUseCase(
    private val clock: Clock
) {
    operator fun invoke(detail: ParadasDetail): ParadasDetail {
        if (detail.status != ParadasStatus.Pending) {
            return detail
        }

        return detail.copy(
            status = ParadasStatus.WaitingDonation,
            arrivedAt = clock.instant(),
            canRegisterArrival = false
        )
    }
}
