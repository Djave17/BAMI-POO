package com.example.recolectores.feature.paradas.domain.model

/**
 * Sucursal operativa asociada a una parada.
 *
 * El dominio separa `Branch` de `Donor` para que la UI pueda mostrar
 * donante y sucursal como conceptos distintos, tal como lo exigen
 * las pantallas de detalle, espera y cierre.
 */
data class Branch(
    val name: String
)
