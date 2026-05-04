package com.example.recolectores.feature.paradas.presentation

import com.example.recolectores.feature.donaciones.domain.model.DonacionesCategory
import com.example.recolectores.feature.donaciones.domain.model.DonacionesItem
import com.example.recolectores.feature.donaciones.presentation.DonacionesFormUiState
import com.example.recolectores.feature.donaciones.presentation.DonationCategoryOptionUiState
import com.example.recolectores.feature.donaciones.presentation.DonationFormItemUiState
import com.example.recolectores.feature.paradas.domain.model.DonationSummary
import com.example.recolectores.feature.paradas.domain.model.ParadasDetail
import com.example.recolectores.feature.paradas.domain.model.ParadasStatus
import com.example.recolectores.feature.paradas.domain.model.WaitingState
import com.example.recolectores.feature.paradas.presentation.completed.ParadasCompletedUiState
import com.example.recolectores.feature.paradas.presentation.components.ParadaStatusTone
import com.example.recolectores.feature.paradas.presentation.components.ParadasInfoCardUiState
import com.example.recolectores.feature.paradas.presentation.components.WaitingTimerCardUiState
import com.example.recolectores.feature.paradas.presentation.detail.ParadasDetailUiState
import com.example.recolectores.feature.paradas.presentation.waiting.WaitingDonorUiState
import java.math.BigDecimal
import java.text.DecimalFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.abs

/**
 * Mapper dominio -> UI del flujo de paradas.
 *
 * Razón de existir:
 * - Mantiene a Compose libre de formateos, parsing o decisiones derivadas.
 * - Documenta en un solo punto cómo se proyecta el contrato de backend
 *   hacia el layout exacto pedido por diseño.
 * - Facilita que una futura API reemplace dummies sin reescribir pantallas.
 */
object ParadaFlowUiMapper {

    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    private val weightFormatter = DecimalFormat("0.00 kg")
    private val valueFormatter = DecimalFormat("$0.00")

    fun toDetailUiState(
        parada: ParadasDetail,
        zoneId: ZoneId
    ): ParadasDetailUiState {
        return ParadasDetailUiState(
            paradaTitle = "Parada #${parada.paradaNumero}",
            statusLabel = parada.status.label,
            statusTone = parada.status.toTone(),
            infoCardState = parada.toInfoCardUiState(zoneId),
            observationText = parada.observationDraft
        )
    }

    fun toWaitingUiState(
        parada: ParadasDetail,
        now: Instant,
        zoneId: ZoneId
    ): WaitingDonorUiState {
        val waitingState = WaitingState(
            waitLimitMinutes = parada.waitLimitMinutes,
            extraWaitMinutesTotal = parada.extraWaitMinutesTotal,
            arrivedAt = parada.arrivedAt,
            now = now
        )

        return WaitingDonorUiState(
            paradaTitle = "Parada #${parada.paradaNumero}",
            statusLabel = parada.status.label,
            statusTone = parada.status.toTone(),
            infoCardState = parada.toInfoCardUiState(zoneId),
            observationText = parada.observationDraft,
            arrivalTimeText = parada.arrivedAt.formatTime(zoneId),
            remainingMinutes = waitingState.remainingMinutes,
            totalAllowedMinutes = waitingState.totalAllowedMinutes,
            isAddExtraTimeEnabled = waitingState.canAddExtraTime,
            timerCardState = waitingState.toTimerCardUiState()
        )
    }

    fun toCompletedUiState(
        parada: ParadasDetail,
        zoneId: ZoneId
    ): ParadasCompletedUiState {
        val summary = parada.donationSummary ?: DonationSummary(
            totalWeightKg = BigDecimal.ZERO,
            totalValue = BigDecimal.ZERO,
            itemCount = 0
        )

        return ParadasCompletedUiState(
            paradaTitle = "Parada #${parada.paradaNumero}",
            statusLabel = parada.status.label,
            statusTone = parada.status.toTone(),
            infoCardState = parada.toInfoCardUiState(zoneId),
            observationText = parada.observationDraft,
            totalWeightText = weightFormatter.format(summary.totalWeightKg),
            totalValueText = valueFormatter.format(summary.totalValue),
            totalItemsText = summary.itemCount.toString()
        )
    }

    fun toDonationUiState(
        parada: ParadasDetail,
        categories: List<DonacionesCategory>,
        items: List<DonacionesItem>,
        fieldErrors: Map<String, String>
    ): DonacionesFormUiState {
        val totalWeight = items.sumOfBigDecimal { item -> item.weightKgInput }
        val totalValue = items.sumOfBigDecimal { item -> item.valueInput }

        return DonacionesFormUiState(
            donorName = parada.donor.name,
            branchName = parada.branch.name,
            categories = categories.map { category ->
                DonationCategoryOptionUiState(
                    id = category.id,
                    label = category.name
                )
            },
            items = items.map { item ->
                DonationFormItemUiState(
                    id = item.id,
                    title = "Información",
                    selectedCategoryId = item.selectedCategoryId,
                    selectedCategoryText = categories.firstOrNull { it.id == item.selectedCategoryId }?.name.orEmpty(),
                    weightInput = item.weightKgInput,
                    valueInput = item.valueInput,
                    categoryError = fieldErrors["${item.id}:category"],
                    weightError = fieldErrors["${item.id}:weight"],
                    valueError = fieldErrors["${item.id}:value"]
                )
            },
            totalWeightText = weightFormatter.format(totalWeight),
            totalValueText = valueFormatter.format(totalValue),
            totalItemsText = items.size.toString(),
            isSaveEnabled = items.isNotEmpty()
        )
    }

    private fun ParadasDetail.toInfoCardUiState(zoneId: ZoneId): ParadasInfoCardUiState {
        return ParadasInfoCardUiState(
            donorName = donor.name,
            branchName = branch.name,
            addressLine = addressLine,
            scheduledArrivalTimeText = scheduledArrivalAt.formatTime(zoneId),
            arrivedAtTimeText = arrivedAt?.formatTime(zoneId)
        )
    }

    private fun WaitingState.toTimerCardUiState(): WaitingTimerCardUiState {
        val absRemaining = abs(remainingMinutes)

        return WaitingTimerCardUiState(
            statusText = if (isExpired) "Vencido" else "Activo",
            timerText = elapsedSeconds.formatAsClock(),
            timerCaption = "transcurrido",
            limitText = "Límite: $waitLimitMinutes minutos",
            remainingText = "${if (remainingMinutes < 0) "-" else ""}$absRemaining minutos restantes",
            helperText = if (isExpired) {
                "El tiempo de espera ha vencido. Puedes dar tiempo extra o cancelar esta parada."
            } else {
                "Aún estás dentro del tiempo de espera permitido."
            },
            progress = progress,
            isExpired = isExpired
        )
    }

    private fun ParadasStatus.toTone(): ParadaStatusTone {
        return when (this) {
            ParadasStatus.Pending -> ParadaStatusTone.Neutral
            ParadasStatus.WaitingDonation -> ParadaStatusTone.Warning
            ParadasStatus.Completed -> ParadaStatusTone.Success
            ParadasStatus.Cancelled -> ParadaStatusTone.Danger
        }
    }

    private fun Instant?.formatTime(zoneId: ZoneId): String {
        return this
            ?.atZone(zoneId)
            ?.toLocalTime()
            ?.format(timeFormatter)
            .orEmpty()
    }

    private fun Long.formatAsClock(): String {
        val minutes = this / 60
        val seconds = this % 60
        return "%02d:%02d".format(minutes, seconds)
    }

    private fun List<DonacionesItem>.sumOfBigDecimal(
        selector: (DonacionesItem) -> String
    ): BigDecimal {
        return fold(BigDecimal.ZERO) { acc, item ->
            acc + (selector(item).toBigDecimalOrNull() ?: BigDecimal.ZERO)
        }
    }
}
