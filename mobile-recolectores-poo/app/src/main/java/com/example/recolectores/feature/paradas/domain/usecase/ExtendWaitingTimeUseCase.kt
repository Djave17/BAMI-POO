package com.example.recolectores.feature.paradas.domain.usecase

import com.example.recolectores.feature.paradas.domain.model.ParadasDetail
import com.example.recolectores.feature.paradas.domain.model.WaitingState
import java.time.Instant

/**
 * Acumula bloques adicionales de espera.
 *
 * La regla del negocio es explícita:
 * - cada clic suma 15 minutos
 * - nunca reemplaza el límite original ya otorgado
 * - solo puede ejecutarse cuando la ventana actual ya venció
 */
class ExtendWaitingTimeUseCase {
    operator fun invoke(
        detail: ParadasDetail,
        now: Instant
    ): ParadasDetail {
        val waitingState = WaitingState(
            waitLimitMinutes = detail.waitLimitMinutes,
            extraWaitMinutesTotal = detail.extraWaitMinutesTotal,
            arrivedAt = detail.arrivedAt,
            now = now
        )

        // Regla de negocio: una extension prematura no cambia la parada.
        // El recolector solo puede sumar margen cuando la ventana vigente ya vencio.
        if (!waitingState.canAddExtraTime) {
            return detail
        }

        // El tiempo extra se acumula sobre el limite original; no reinicia arrivedAt.
        return detail.copy(
            extraWaitMinutesTotal = detail.extraWaitMinutesTotal + WaitingState.EXTRA_TIME_INCREMENT_MINUTES
        )
    }
}
