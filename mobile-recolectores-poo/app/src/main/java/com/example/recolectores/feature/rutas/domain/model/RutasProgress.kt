package com.example.recolectores.feature.rutas.domain.model

/**
 * Progreso derivado de la ruta activa.
 *
 * Backend entrega estados por parada; la app calcula este resumen para que
 * inicio y rutas muestren el mismo avance sin duplicar la regla.
 */
data class RutasProgress(
    val completedStops: Int,
    val totalStops: Int
)
