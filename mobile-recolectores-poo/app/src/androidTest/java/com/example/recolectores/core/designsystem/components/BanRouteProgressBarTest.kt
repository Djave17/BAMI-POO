package com.example.recolectores.core.designsystem.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.width
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.states.RouteProgressUiState
import com.example.recolectores.core.designsystem.theme.BanProgressTrack
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanSurface
import com.example.recolectores.core.designsystem.theme.BanTheme
import com.example.recolectores.core.navigation.BanMainScaffold
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test

class BanRouteProgressBarTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun progress_bar_no_muestra_stop_indicator_verde_al_final() {
        val progressState = RouteProgressUiState(completedStops = 1, totalStops = 4)

        composeRule.setContent {
            BanTheme {
                BanRouteProgressBar(
                    progressState = progressState,
                    modifier = androidx.compose.ui.Modifier.width(240.dp)
                )
            }
        }

        val image = composeRule
            .onNode(
                hasProgressBarRangeInfo(
                    ProgressBarRangeInfo(
                        current = progressState.progress,
                        range = 0f..1f
                    )
                )
            )
            .captureToImage()

        val centerY = image.height / 2
        val rightTailHasGreenStopIndicator =
            (image.width - 20 until image.width)
                .filter { it >= 0 }
                .any { x -> image.colorAt(x, centerY) == BanGreen }

        assertFalse(rightTailHasGreenStopIndicator)
    }

    @Test
    fun shell_autenticado_muestra_blanco_en_el_inset_superior() {
        composeRule.setContent {
            BanTheme {
                BanMainScaffold()
            }
        }

        val image = composeRule.onRoot().captureToImage()
        val topInsetColor = image.colorAt(10, 10)

        assertEquals(BanSurface, topInsetColor)
    }
}

private fun ImageBitmap.colorAt(x: Int, y: Int): Color =
    toPixelMap()[x, y]
