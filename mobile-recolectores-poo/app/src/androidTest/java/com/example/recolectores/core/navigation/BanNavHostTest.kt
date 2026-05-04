package com.example.recolectores.core.navigation

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.recolectores.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BanNavHostTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun login_valido_navega_al_shell_autenticado() {
        // Esta prueba evita regresiones entre el grafo raiz y el grafo autenticado.
        composeRule.onNodeWithText("Iniciar sesion").assertIsDisplayed()

        composeRule.onNodeWithText("tu@email.com").performTextInput("recolector@ban.org")
        composeRule.onNodeWithText("********").performTextInput("Segura123")
        composeRule.onNodeWithText("Ingresar").performClick()

        composeRule.onNodeWithText("Resumen de la jornada del recolector.").assertIsDisplayed()
        composeRule.onAllNodesWithText("Iniciar sesion").assertCountEquals(0)
    }
}
