package com.example.recolectores.feature.paradas.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanBackground
import com.example.recolectores.core.designsystem.theme.BanSurface

/**
 * Scaffold compartido para el flujo de paradas.
 *
 * Resuelve la estructura repetida de los mockups:
 * - header blanco fijo
 * - cuerpo gris desplazable
 * - barra inferior fija con acciones del recolector
 */
@Composable
fun ParadaFlowScreenScaffold(
    topBar: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = LocalBanSpacing.screenHorizontalPadding,
        vertical = LocalBanSpacing.screenVerticalPadding
    ),
    bottomBar: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val spacing = LocalBanSpacing
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = BanBackground,
        topBar = {
            Surface(color = BanSurface) {
                topBar()
            }
        },
        bottomBar = {
            if (bottomBar != null) {
                Surface(color = BanBackground) {
                    androidx.compose.foundation.layout.Column(
                        modifier = Modifier.padding(
                            horizontal = spacing.medium,
                            vertical = spacing.medium
                        ).navigationBarsPadding()
                    ) {
                        bottomBar()
                    }
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = BanBackground
        ) {
            androidx.compose.foundation.layout.Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(contentPadding)
            ) {
                content()
            }
        }
    }
}
