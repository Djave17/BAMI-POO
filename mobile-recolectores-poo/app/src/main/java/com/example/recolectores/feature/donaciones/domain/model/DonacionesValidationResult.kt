package com.example.recolectores.feature.donaciones.domain.model

/**
 * Resultado de validación del formulario.
 *
 * `fieldErrors` se indexa por id de item y nombre de campo para que la
 * UI pueda asociar el error con precisión sin mezclar reglas de negocio
 * dentro de los composables.
 */
data class DonacionesValidationResult(
    val isValid: Boolean,
    val fieldErrors: Map<String, String> = emptyMap()
)
