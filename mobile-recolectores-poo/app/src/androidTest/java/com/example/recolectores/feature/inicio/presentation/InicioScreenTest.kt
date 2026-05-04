package com.example.recolectores.feature.inicio.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertLeftPositionInRootIsEqualTo
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.theme.BanTheme
import com.example.recolectores.feature.inicio.data.dummy.InicioDummyData
import org.junit.Rule
import org.junit.Test

class InicioScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun header_ocupa_el_ancho_completo_sin_margen_lateral() {
        composeRule.setContent {
            BanTheme {
                InicioScreen(
                    uiState = InicioDummyData.initialState,
                    onAcceptOperationalRequest = {},
                    onDenyOperationalRequest = {},
                    onNextStopClick = {}
                )
            }
        }

        composeRule
            .onNodeWithTag("inicio_header_surface")
            .assertLeftPositionInRootIsEqualTo(0.dp)
    }
}
