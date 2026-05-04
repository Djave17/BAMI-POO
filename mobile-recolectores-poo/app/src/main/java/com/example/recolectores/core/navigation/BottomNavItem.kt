package com.example.recolectores.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Route
import androidx.compose.ui.graphics.vector.ImageVector


/**
 * Elemento de navegación inferior
 *
 * Propósito: Representar cada opción visible dentro de la barra inferior del área autenticada.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Describir la ruta, la etiqueta y los iconos de cada sección principal.
 *   - Permitir que el shell autenticado pinte pestañas consistentes para las pantallas internas.
 *   - No ejecutar navegación por sí mismo ni consultar datos remotos o locales.
 * 
 */
data class BottomNavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
){

}

/**
 * Opciones principales del recolector
 *
 * Propósito: Exponer la base inicial de pestañas disponibles dentro del shell autenticado.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Listar las rutas internas que el recolector puede abrir desde la barra inferior.
 *   - Servir como configuración simple para el `BanMainScaffold`.
 *   - No reemplazar el grafo raíz ni decidir reglas de acceso.
 * 
 */
val bottomNavItems = listOf(
    BottomNavItem(
        route = BanRoutes.Inicio,
        label = "Inicio",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home

    ),
    BottomNavItem(
        route = BanRoutes.Rutas,
        label = "Rutas",
        selectedIcon = Icons.Filled.Route,
        unselectedIcon = Icons.Outlined.Route

    ),
    BottomNavItem(
        route = BanRoutes.Perfil,
        label = "Perfil",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person

    )
)
