package com.example.recolectores.feature.paradas.data.remote.dto

/**
 * DTO remoto preparado para reflejar el payload del endpoint de detalle.
 *
 * Decisiones de modelado:
 * - Los timestamps permanecen crudos como `String?` porque así suelen
 *   viajar en JSON y el parseo se concentra en el mapper.
 * - La UI no debe leer este DTO de forma directa.
 */
data class ParadasDetailDto(
    val paradaId: String,
    val paradaNumero: Int,
    val status: String,
    val donorName: String,
    val branchName: String,
    val addressLine: String,
    val scheduledArrivalAt: String?,
    val arrivedAt: String?,
    val waitLimitMinutes: Int = 40,
    val extraWaitMinutesTotal: Int = 0,
    val observationDraft: String = "",
    val completedAt: String? = null,
    val donationSummary: DonationSummaryDto? = null,
    val canReportIncident: Boolean = true,
    val canCancelStop: Boolean = true,
    val canRegisterArrival: Boolean = false
)

data class DonationSummaryDto(
    val totalWeightKg: String,
    val totalValue: String,
    val itemCount: Int
)
