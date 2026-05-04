package com.example.recolectores.core.designsystem.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.theme.BanBackground

/**
 * Contenedor simple de pantalla
 *
 * Propósito: Entregar una base visual uniforme para pantallas internas que no requieren encabezado separado.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Aplicar padding, fondo y soporte de scroll de manera homogénea.
 *   - Reducir código repetido para que las pantallas se enfoquen en mostrar estado.
 *   - No contener reglas de negocio ni acceder directo a API, Room, DataStore o WebSocket.
 * 
 */
@Composable
fun BanScreenContainer(
    modifier: Modifier = Modifier,
    scrollable: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = LocalBanSpacing.extraLarge,
        vertical = LocalBanSpacing.large
    ),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(16.dp),
    content: @Composable ColumnScope.() -> Unit //Aca seteamos que la plantilla de layout pueda recibir componentes
    //composables y  que el contenido se ejecuta dentro del contexto de una Column. No devuelve nada solo dibuja la UI -> UNIT
) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BanBackground
    ) {
        Column(
            modifier = modifier //usa el modifier que viene desde afuera.
                .fillMaxSize()
                .padding(contentPadding) // Agrega espacio interno a la pantalla. Por defecto todas las pantallas van a tener 20 y 16 de pading
                .then( // Nueva instancia de modifier
                    if (scrollable) {
                        Modifier.verticalScroll(scrollState)
                    } else {
                        Modifier
                    }
                ),
            verticalArrangement = verticalArrangement,
            content = content
        )
    }
}
