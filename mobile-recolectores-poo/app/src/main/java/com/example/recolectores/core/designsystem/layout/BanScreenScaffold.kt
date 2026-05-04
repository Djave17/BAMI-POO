package com.example.recolectores.core.designsystem.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.theme.BanBackground
import com.example.recolectores.core.designsystem.theme.BanSurface

/**
 * Plantilla con encabezado
 *
 * Propósito: Ofrecer una estructura común para pantallas que necesitan encabezado fijo y contenido principal separado.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Aplicar espaciados, fondo y zonas seguras de forma consistente.
 *   - Separar el bloque de cabecera del contenido para que las pantallas de negocio solo definan UI.
 *   - No cargar datos ni acceder directo a API, Room, DataStore o WebSocket.
 * 
 */
@Composable
fun BanScreenScaffold(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    scrollable: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = LocalBanSpacing.screenHorizontalPadding,
        vertical = LocalBanSpacing.screenVerticalPadding
    ),
    content: @Composable ColumnScope.() -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()

    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = BanSurface
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = LocalBanSpacing.headerHorizontalPadding,
                    vertical = LocalBanSpacing.headerVerticalPadding
                )
            ) {
                header()
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = BanBackground
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .then(
                        if (scrollable) {
                            Modifier.verticalScroll(scrollState)
                        } else {
                            Modifier
                        }
                    ),
                content = content
            )
        }
    }
}
