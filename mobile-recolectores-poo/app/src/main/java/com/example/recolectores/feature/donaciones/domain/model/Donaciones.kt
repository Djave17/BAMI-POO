package com.example.recolectores.feature.donaciones.domain.model

/**
 * Borrador completo del formulario de donación.
 *
 * Esta estructura representa exactamente lo que la vista necesita editar
 * antes de persistir: contexto de parada más items capturados.
 */
data class Donaciones(
    val paradaId: String,
    val donorName: String,
    val branchName: String,
    val items: List<DonacionesItem>
)
