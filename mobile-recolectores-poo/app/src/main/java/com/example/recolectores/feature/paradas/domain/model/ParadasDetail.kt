package com.example.recolectores.feature.paradas.domain.model

import java.time.Instant

/**
 * Agregado principal del flujo de parada.
 *
 * Este modelo concentra exactamente la información que hoy consumen las
 * pantallas del recolector y la que mañana deberá venir desde API.
 *
 * Criterio de diseño:
 * - Los timestamps viven tipados como `Instant` para cálculo seguro.
 * - El borrador de observaciones vive en el agregado para sobrevivir
 *   entre detalle, espera, formulario de donación y cierre.
 * - Los flags `can*` evitan que Compose tenga que inferir reglas de negocio.
 */
data class ParadasDetail(
    val paradaId: String,
    val paradaNumero: Int,
    val status: ParadasStatus,
    val donor: Donor,
    val branch: Branch,
    val addressLine: String,
    val scheduledArrivalAt: Instant?,
    val arrivedAt: Instant?,
    val waitLimitMinutes: Int,
    val extraWaitMinutesTotal: Int,
    val observationDraft: String,
    val completedAt: Instant?,
    val donationSummary: DonationSummary?,
    val canReportIncident: Boolean,
    val canCancelStop: Boolean,
    val canRegisterArrival: Boolean
)
