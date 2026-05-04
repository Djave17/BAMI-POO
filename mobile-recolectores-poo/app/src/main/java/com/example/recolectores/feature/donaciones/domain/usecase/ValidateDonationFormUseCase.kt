package com.example.recolectores.feature.donaciones.domain.usecase

import com.example.recolectores.feature.donaciones.domain.model.Donaciones
import com.example.recolectores.feature.donaciones.domain.model.DonacionesValidationResult
import java.math.BigDecimal

/**
 * Valida el formulario editable de donaciones.
 *
 * Se ejecuta fuera de Compose para que:
 * - la UI no repita reglas
 * - las pruebas cubran el contrato con facilidad
 * - el feature quede listo para reutilizar la validación en otros puntos
 */
class ValidateDonationFormUseCase {

    operator fun invoke(draft: Donaciones): DonacionesValidationResult {
        val errors = buildMap {
            draft.items.forEach { item ->
                if (item.selectedCategoryId.isNullOrBlank()) {
                    put("${item.id}:category", "Selecciona una categoría.")
                }

                val weight = item.weightKgInput.toBigDecimalOrNull()
                if (weight == null || weight <= BigDecimal.ZERO) {
                    put("${item.id}:weight", "Ingresa un peso válido.")
                }

                val value = item.valueInput.toBigDecimalOrNull()
                if (value == null || value <= BigDecimal.ZERO) {
                    put("${item.id}:value", "Ingresa un valor válido.")
                }
            }
        }

        return DonacionesValidationResult(
            isValid = errors.isEmpty(),
            fieldErrors = errors
        )
    }
}
