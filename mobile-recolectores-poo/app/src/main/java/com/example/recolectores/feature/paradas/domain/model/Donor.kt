package com.example.recolectores.feature.paradas.domain.model

/**
 * Donante visible en la parada.
 *
 * Mantener este dato como objeto de dominio evita que la UI dependa
 * de nombres de campos remotos y deja margen para crecer con contacto,
 * documento o metadatos operativos sin romper contratos existentes.
 */
data class Donor(
    val name: String
)
