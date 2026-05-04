package com.example.recolectores.feature.paradas.domain.model

/**
 * Estados operativos soportados por el flujo.
 *
 * `Cancelled` queda preparado aunque esta iteración solo emita callback
 * cuando el recolector pulse la acción correspondiente.
 */
enum class ParadasStatus(
    val label: String
) {
    Pending("Pendiente"),
    WaitingDonation("Esperando Donación"),
    Completed("Completada"),
    Cancelled("Cancelada")
}
