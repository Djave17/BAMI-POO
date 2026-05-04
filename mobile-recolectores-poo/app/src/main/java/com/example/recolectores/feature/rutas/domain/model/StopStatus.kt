package com.example.recolectores.feature.rutas.domain.model

/**
 * Estados visibles de una parada dentro de la pantalla de rutas.
 *
 * `ExpiredWait` se muestra solo cuando backend o el flujo de paradas ya
 * cerraron la visita por espera vencida. El simple vencimiento del timer no
 * cambia automaticamente este estado.
 */
enum class StopStatus(
    val label: String
) {
    Pending("Pendiente"),
    WaitingDonation("Esperando Donacion"),
    Completed("Completada"),
    ExpiredWait("Espera Vencida")
}
