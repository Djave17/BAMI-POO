package com.example.recolectores.feature.donaciones.data.dummy

import com.example.recolectores.feature.donaciones.domain.model.DonacionesCategory

/**
 * Fuente temporal de categorías para el formulario.
 *
 * La estructura es deliberadamente equivalente a la que entregaría un
 * endpoint para que el reemplazo futuro sea mecánico y no un rediseño.
 */
object DonacionesDummyData {

    val categories = listOf(
        DonacionesCategory(id = "frutas_verduras", name = "Frutas y Verdura"),
        DonacionesCategory(id = "panaderia", name = "Panadería"),
        DonacionesCategory(id = "lacteos", name = "Lácteos"),
        DonacionesCategory(id = "abarrotes", name = "Abarrotes")
    )
}
