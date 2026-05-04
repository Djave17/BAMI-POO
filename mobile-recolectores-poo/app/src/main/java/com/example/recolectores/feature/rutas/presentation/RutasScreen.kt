package com.example.recolectores.feature.rutas.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanBackground
import com.example.recolectores.core.designsystem.theme.BanSurface
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanTextSecondary
import com.example.recolectores.feature.rutas.presentation.components.RutasHeader
import com.example.recolectores.feature.rutas.presentation.components.StopList

/**
 * Pantalla de rutas.
 *
 * Organiza la vista principal de rutas y paradas asignadas.
 *
 * Responsabilidades:
 * - Recibir el estado preparado desde RutasRoute/ViewModel.
 * - Ordenar header, progreso, listado de paradas y acción inferior.
 * - Delegar el diseño específico a componentes de rutas.
 * - No cargar datos directamente desde API, Room, DataStore ni WebSocket.
 */
@Composable
fun RutasScreen(
    uiState: RutasUiState,
    onBackClick: () -> Unit,
    onStopClick: (String) -> Unit,
    onSummaryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalBanSpacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BanBackground)
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = BanSurface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = spacing.headerHorizontalPadding,
                        vertical = spacing.headerVerticalPadding
                    ),
                verticalArrangement = Arrangement.spacedBy(spacing.large)
            ) {
                RutasHeader(
                    routeName = uiState.routeName,
                    routeCode = uiState.routeCode,
                    onBackClick = onBackClick
                )


            }
        }

        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Cargando ruta...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BanTextSecondary
                    )
                }
            }

            uiState.errorMessage != null -> {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.errorMessage,
                        style = MaterialTheme.typography.bodyMedium,
                        color = BanTextPrimary
                    )
                }
            }

            uiState.stops.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay paradas asignadas.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BanTextSecondary
                    )
                }
            }

            else -> {
                StopList(
                    stops = uiState.stops,
                    onStopClick = onStopClick,
                    modifier = Modifier.weight(1f)
                )
            }
        }


    }
}