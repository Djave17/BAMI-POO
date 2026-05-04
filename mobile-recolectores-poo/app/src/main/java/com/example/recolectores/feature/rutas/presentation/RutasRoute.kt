package com.example.recolectores.feature.rutas.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RutasRoute(
    viewModel: RutasViewModel = viewModel(),
    onBackClick: () -> Unit,
    onStopClick: (String) -> Unit,
    onSummaryClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    RutasScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onStopClick = onStopClick,
        onSummaryClick = onSummaryClick
    )
}