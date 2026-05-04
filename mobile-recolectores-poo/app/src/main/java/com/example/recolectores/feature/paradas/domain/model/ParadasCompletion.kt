package com.example.recolectores.feature.paradas.domain.model

import java.time.Instant

/**
 * Snapshot final que resume el cierre de la parada.
 *
 * Aunque hoy la vista de completado muestra un resumen simple, este
 * contrato deja preparada la evolución hacia confirmaciones remotas
 * o recibos locales sin que la pantalla tenga que reconstruir datos.
 */
data class ParadasCompletion(
    val paradaId: String,
    val completedAt: Instant?,
    val observation: String,
    val donationSummary: DonationSummary?
)
