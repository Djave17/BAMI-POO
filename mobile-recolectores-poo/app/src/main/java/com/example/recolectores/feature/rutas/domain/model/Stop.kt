package com.example.recolectores.feature.rutas.domain.model

import java.time.Instant

/**
 * Parada visible dentro de la ruta activa.
 *
 * `visitTime` representa la hora de visita del backend. En los dummies se
 * alimenta desde `scheduledArrivalAt` para que la misma regla funcione antes
 * de conectar la API real. Los estados son visibles para la lista de rutas;
 * otras reglas operativas permanecen en el flujo de paradas.
 */
data class Stop(
    val id: String,
    val order: Int,
    val donorName: String,
    val branchName: String,
    val addressLine: String,
    val visitTime: Instant?,
    val status: StopStatus,
    val arrivedAt: Instant?,
    val waitLimitMinutes: Int,
    val extraWaitMinutesTotal: Int
)
