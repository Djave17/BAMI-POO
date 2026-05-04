package com.example.recolectores.feature.rutas.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.feature.rutas.presentation.RouteStopUiState

/**
 * Lista de paradas de la ruta.
 *
 * Dibuja el título de sección y las tarjetas de paradas.
 */
@Composable
fun StopList(
    stops: List<RouteStopUiState>,
    onStopClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalBanSpacing

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            start = spacing.large,
            top = spacing.large,
            end = spacing.large,
            bottom = spacing.large
        ),
        verticalArrangement = Arrangement.spacedBy(spacing.medium)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.small)
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    tint = BanGreen,
                    modifier = Modifier.size(spacing.extraLarge)
                )

                Text(
                    text = "Paradas",
                    style = MaterialTheme.typography.titleLarge,
                    color = BanTextPrimary
                )
            }
        }

        items(
            items = stops,
            key = { stop -> stop.id }
        ) { stop ->
            StopListItem(
                stop = stop,
                onClick = {
                    onStopClick(stop.id)
                }
            )
        }
    }
}