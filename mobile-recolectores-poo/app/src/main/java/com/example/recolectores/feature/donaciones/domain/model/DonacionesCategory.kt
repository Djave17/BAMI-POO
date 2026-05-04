package com.example.recolectores.feature.donaciones.domain.model

/**
 * Categoría elegible para un item de donación.
 *
 * Este contrato está listo para poblarse desde endpoint sin que la UI
 * dependa de literales quemados dentro de Compose.
 */
data class DonacionesCategory(
    val id: String,
    val name: String
)
