package com.example.recolectores.feature.paradas.domain.model

import java.math.BigDecimal

/**
 * Resumen agregado de la donación capturada en la parada.
 *
 * Se modela con `BigDecimal` para que el valor monetario y los pesos
 * permanezcan exactos durante el agregado y no dependan de redondeos
 * accidentales de punto flotante.
 */
data class DonationSummary(
    val totalWeightKg: BigDecimal,
    val totalValue: BigDecimal,
    val itemCount: Int
)
