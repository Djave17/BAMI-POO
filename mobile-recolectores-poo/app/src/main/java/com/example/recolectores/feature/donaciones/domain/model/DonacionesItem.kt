package com.example.recolectores.feature.donaciones.domain.model

/**
 * Item editable del formulario de donación.
 *
 * Las cantidades permanecen como `String` mientras el usuario edita
 * porque ese es el contrato natural de un campo de texto. La validación
 * y el parseo ocurren después, en la capa de casos de uso.
 */
data class DonacionesItem(
    val id: String,
    val selectedCategoryId: String?,
    val weightKgInput: String,
    val valueInput: String
)
