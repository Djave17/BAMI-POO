package com.example.recolectores.feature.rutas.domain.model

/**
 * Ruta activa asignada al recolector.
 *
 * Este modelo es la fuente comun que deben observar inicio, rutas y paradas.
 * `id`, `name`, `code` y `stops` vienen de backend o del dummy compatible
 * con backend. El progreso se deriva en la app para evitar que cada pantalla
 * cuente las paradas con una regla distinta.
 */
data class Rutas(
    val id: String,
    val name: String,
    val code: String,
    val stops: List<Stop>
) {
    val progress: RutasProgress
        get() = RutasProgress(
            completedStops = stops.count { stop -> stop.status == StopStatus.Completed },
            totalStops = stops.size
        )
}
