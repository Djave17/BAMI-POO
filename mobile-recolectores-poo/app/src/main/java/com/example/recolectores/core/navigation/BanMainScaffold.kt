package com.example.recolectores.core.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.recolectores.core.designsystem.components.BanBottomBar
import com.example.recolectores.core.designsystem.theme.BanSurface
import com.example.recolectores.feature.inicio.data.dummy.InicioDummyData
import com.example.recolectores.feature.inicio.presentation.InicioScreen
import com.example.recolectores.feature.paradas.data.dummy.ParadasDummyData
import com.example.recolectores.feature.paradas.data.repository.ParadasRepositoryImpl
import com.example.recolectores.feature.paradas.presentation.paradaFlowDestinations
import com.example.recolectores.feature.perfil.presentation.PerfilRoute
import com.example.recolectores.feature.rutas.presentation.RutasRoute
import java.time.ZoneId

/**
 * Shell autenticado principal.
 *
 * Orquesta la barra inferior y el grafo interno del area autenticada.
 */
@Composable
fun BanMainScaffold(
    modifier: Modifier = Modifier,
    //verificar logout
    onLogoutCompleted: () -> Unit = {}
) {
    val navController = rememberNavController()
    val zoneId = remember { ZoneId.systemDefault() }
    val stopFlowClock = remember(zoneId) { ParadasDummyData.createDemoClock(zoneId) }
    val paradasRepository = remember(zoneId) {
        ParadasRepositoryImpl(
            seedStops = ParadasDummyData.createSeedStops(zoneId)
        )
    }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val bottomRoutes = bottomNavItems.map { item -> item.route }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = BanSurface,
        contentWindowInsets = WindowInsets.safeDrawing.only(WindowInsetsSides.Top),
        bottomBar = {
            if (currentRoute in bottomRoutes) {
                BanBottomBar(
                    items = bottomNavItems,
                    currentRoute = currentRoute,
                    modifier = modifier,
                    onItemClick = { item ->
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                popUpTo(BanRoutes.Inicio) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BanRoutes.Inicio,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            composable(route = BanRoutes.Inicio) {
                InicioScreen(
                    uiState = InicioDummyData.initialState,
                    onAcceptOperationalRequest = {},
                    onDenyOperationalRequest = {},
                    onNextStopClick = {}
                )
            }

            composable(route = BanRoutes.Rutas) {
                RutasRoute(
                    onBackClick = { navController.popBackStack() },
                    onStopClick = {
                        navController.navigate(BanRoutes.paradaFlow(it))
                    },
                    onSummaryClick = {
                        // Navegacion pendiente al resumen de ruta.
                    }
                )
            }

            composable(route = BanRoutes.Perfil) {
                // Perfil no conoce el NavController raiz; solo avisa que el logout finalizo.
                PerfilRoute(
                    onLogoutCompleted = onLogoutCompleted
                )
            }

            paradaFlowDestinations(
                navController = navController,
                repository = paradasRepository,
                clock = stopFlowClock,
                zoneId = zoneId
            )
        }
    }
}
