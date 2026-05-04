package com.example.recolectores.feature.paradas.domain.model

import java.time.Duration
import java.time.Instant
import kotlin.math.max

/**
 * Estado derivado del temporizador de espera.
 *
 * La regla de negocio se modela aquí, fuera de Compose, para que la UI
 * solo renderice resultados ya calculados. Esto es importante porque el
 * temporizador debe ser "timestamp-driven": la verdad vive en
 * `arrivedAt + waitLimitMinutes + extraWaitMinutesTotal`, no en un
 * contador local de la vista.
 */
data class WaitingState(
    val waitLimitMinutes: Int,
    val extraWaitMinutesTotal: Int,
    val arrivedAt: Instant?,
    val now: Instant
) {

    init {
        require(waitLimitMinutes >= 0) {
            "waitLimitMinutes no puede ser negativo."
        }
        require(extraWaitMinutesTotal >= 0) {
            "extraWaitMinutesTotal no puede ser negativo."
        }
        require(extraWaitMinutesTotal % EXTRA_TIME_INCREMENT_MINUTES == 0) {
            "El tiempo extra debe acumularse en bloques de 15 minutos."
        }
    }

    /**
     * Expone si ya existe una llegada registrada.
     *
     * Mientras `arrivedAt` sea nulo, la parada aún no ha iniciado su
     * ventana de espera y la UI no debe comportarse como timer activo.
     */
    val hasActiveWait: Boolean = arrivedAt != null

    /**
     * Minutos totales permitidos para la espera.
     *
     * El tiempo extra se acumula y nunca reemplaza el límite original.
     */
    val totalAllowedMinutes: Int = waitLimitMinutes + extraWaitMinutesTotal

    /**
     * Ventana total expresada en segundos para formateo de UI.
     */
    val totalAllowedSeconds: Long = totalAllowedMinutes * 60L

    /**
     * Tiempo transcurrido desde la llegada real del recolector.
     *
     * Se usa `Duration` contra `Instant` para que la recomputación sea
     * segura al volver de background, navegar o rehidratar desde API.
     */
    val elapsedMinutes: Int = arrivedAt
        ?.let { Duration.between(it, now).toMinutes().toInt() }
        ?.coerceAtLeast(0)
        ?: 0

    /**
     * Tiempo transcurrido en segundos.
     *
     * La vista usa este dato para renderizar `mm:ss` sin inventar
     * su propio contador local como fuente de verdad.
     */
    val elapsedSeconds: Long = arrivedAt
        ?.let { Duration.between(it, now).seconds }
        ?.coerceAtLeast(0L)
        ?: 0L

    /**
     * Minutos restantes antes de vencer.
     *
     * Este valor puede ser negativo intencionalmente: la deuda de tiempo
     * se muestra en UI para explicar cuán vencida está la espera.
     *
     * No debe usarse como contador local; siempre se deriva de timestamps.
     */
    val remainingMinutes: Int = totalAllowedMinutes - elapsedMinutes

    /**
     * Tiempo restante en segundos.
     *
     * Puede ser negativo por la misma razón que `remainingMinutes`:
     * necesitamos comunicar visualmente cuánta espera quedó vencida.
     */
    val remainingSeconds: Long = totalAllowedSeconds - elapsedSeconds

    /**
     * La espera se considera vencida cuando ya no quedan minutos.
     *
     * Esta marca habilita acciones del flujo, pero no cambia por si sola
     * el estado de la parada a "Espera Vencida". Ese cierre ocurre cuando
     * el recolector cancela una visita vencida.
     */
    val isExpired: Boolean = hasActiveWait && remainingMinutes <= 0

    /**
     * El tiempo extra solo puede concederse cuando la ventana vigente ya venció.
     *
     * La UI usa este flag para deshabilitar el botón y el dominio lo usa
     * para rechazar extensiones prematuras aunque alguien intente dispararlas.
     *
     * Cada extension agrega un bloque fijo y nunca reinicia `arrivedAt`.
     */
    val canAddExtraTime: Boolean = isExpired

    /**
     * Progreso lineal del tiempo consumido.
     *
     * Se acota a `1f` cuando el tiempo ya venció para evitar barras
     * visualmente rotas, pero la deuda real permanece en `remainingMinutes`.
     */
    val progress: Float = if (totalAllowedMinutes == 0) {
        1f
    } else {
        max(elapsedMinutes.toFloat() / totalAllowedMinutes.toFloat(), 0f).coerceAtMost(1f)
    }

    companion object {
        /**
         * Regla operativa: cada aprobacion de tiempo extra suma 15 minutos.
         */
        const val EXTRA_TIME_INCREMENT_MINUTES = 15
    }
}
