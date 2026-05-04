package com.example.recolectores.feature.donaciones.domain.usecase

import com.example.recolectores.feature.donaciones.domain.model.Donaciones
import com.example.recolectores.feature.paradas.domain.model.DonationSummary
import java.math.BigDecimal

/**
 * Consolida un borrador válido en un resumen persistible.
 *
 * En esta iteración no escribe todavía en backend; su responsabilidad es
 * traducir el formulario ya validado al contrato de dominio que consume
 * el cierre de parada.
 */
class SaveDonationUseCase {

    operator fun invoke(draft: Donaciones): DonationSummary {
        val totalWeight = draft.items.fold(BigDecimal.ZERO) { acc, item ->
            acc + item.weightKgInput.toBigDecimal()
        }

        val totalValue = draft.items.fold(BigDecimal.ZERO) { acc, item ->
            acc + item.valueInput.toBigDecimal()
        }

        return DonationSummary(
            totalWeightKg = totalWeight,
            totalValue = totalValue,
            itemCount = draft.items.size
        )
    }
}
