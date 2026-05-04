package com.example.recolectores.feature.paradas.domain.model

import java.time.Instant
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class WaitingStateTest {

    private val now = Instant.parse("2026-05-01T15:00:00Z")

    @Test
    fun sin_llegada_registrada_el_timer_permanece_inactivo() {
        val state = WaitingState(
            waitLimitMinutes = 40,
            extraWaitMinutesTotal = 0,
            arrivedAt = null,
            now = now
        )

        assertFalse(state.hasActiveWait)
        assertEquals(0, state.elapsedMinutes)
        assertEquals(40, state.remainingMinutes)
        assertFalse(state.isExpired)
    }

    @Test
    fun cuando_el_tiempo_sigue_vigente_calcula_minutos_restantes() {
        val state = WaitingState(
            waitLimitMinutes = 40,
            extraWaitMinutesTotal = 0,
            arrivedAt = now.minusSeconds(20 * 60L),
            now = now
        )

        assertTrue(state.hasActiveWait)
        assertEquals(20, state.elapsedMinutes)
        assertEquals(20, state.remainingMinutes)
        assertFalse(state.isExpired)
        assertFalse(state.canAddExtraTime)
    }

    @Test
    fun cuando_se_cumple_exactamente_el_limite_se_marca_como_vencido() {
        val state = WaitingState(
            waitLimitMinutes = 40,
            extraWaitMinutesTotal = 0,
            arrivedAt = now.minusSeconds(40 * 60L),
            now = now
        )

        assertEquals(40, state.elapsedMinutes)
        assertEquals(0, state.remainingMinutes)
        assertTrue(state.isExpired)
        assertTrue(state.canAddExtraTime)
    }

    @Test
    fun cuando_supera_el_limite_mantiene_restante_negativo_para_explicar_el_vencimiento() {
        val state = WaitingState(
            waitLimitMinutes = 40,
            extraWaitMinutesTotal = 0,
            arrivedAt = now.minusSeconds(45 * 60L),
            now = now
        )

        assertEquals(45, state.elapsedMinutes)
        assertEquals(-5, state.remainingMinutes)
        assertTrue(state.isExpired)
    }

    @Test
    fun acumula_multiples_extensiones_de_quince_minutos() {
        val state = WaitingState(
            waitLimitMinutes = 40,
            extraWaitMinutesTotal = 30,
            arrivedAt = now.minusSeconds(50 * 60L),
            now = now
        )

        assertEquals(70, state.totalAllowedMinutes)
        assertEquals(50, state.elapsedMinutes)
        assertEquals(20, state.remainingMinutes)
        assertFalse(state.isExpired)
    }
}
