package com.example.recolectores.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recolectores.feature.autenticacion.presentation.LoginRoute

/**
 * Grafo raíz de navegación
 *
 * Propósito: Definir la navegación de más alto nivel entre el acceso inicial y el área autenticada.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Declarar el grafo raíz que decide cuándo se muestra login y cuándo se entra al shell autenticado.
 *   - Coordinar la transición segura desde inicio de sesión hacia el contenedor principal.
 *   - No administrar datos de negocio ni acceder directo a API, Room, DataStore o WebSocket.
 * 
 *
 * Nota: Este archivo representa el grafo raíz. El shell autenticado vive dentro de `main` y las pantallas internas cuelgan de ese contenedor, no de este nivel.
 */
@Composable
fun BanNavHost(
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit = {}
) {
    val navController = rememberNavController()

    // Este host es el shell de entrada mientras las demas features siguen siendo placeholders.
    NavHost(
        navController = navController,
        startDestination = BanRoutes.InicioSesion,
        modifier = modifier
    ) {
        composable(route = BanRoutes.InicioSesion) {
            LoginRoute(
                onLoginSuccess = {
                    navController.navigate(BanRoutes.Main) {
                        popUpTo(BanRoutes.InicioSesion) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // `main` es el contenedor del area autenticada; `inicio` vive dentro de ese scaffold.
        composable(route = BanRoutes.Main) {
            BanMainScaffold(
                onLogoutCompleted = {
                    // Logout debe regresar al login y remover el shell autenticado del historial.
                    navController.navigate(BanRoutes.InicioSesion) {
                        popUpTo(BanRoutes.Main) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
