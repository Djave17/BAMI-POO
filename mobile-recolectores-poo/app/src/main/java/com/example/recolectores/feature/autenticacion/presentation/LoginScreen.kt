package com.example.recolectores.feature.autenticacion.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.recolectores.R
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanTextSecondary
import com.example.recolectores.feature.autenticacion.presentation.components.LoginActionButton
import com.example.recolectores.feature.autenticacion.presentation.components.LoginForm
import com.example.recolectores.feature.autenticacion.presentation.components.LoginHeader

/**
 * Pantalla de inicio de sesión
 *
 * Propósito: Dibujar la interfaz de acceso del recolector usando un `UiState` ya preparado.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Mostrar campos, mensajes y acciones del formulario sin conocer reglas de negocio.
 *   - Delegar eventos del usuario al exterior para que el ViewModel los procese.
 *   - No acceder directo a API, Room, DataStore o WebSocket.
 * 
 */
@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit
) {
    val spacing = LocalBanSpacing
    val cardTop = 132.dp
    val logoSize = 100.dp
    val cardHorizontalPadding = 24.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BanGreen)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = cardTop)
                .zIndex(1f),
            color = White,
            shape = RoundedCornerShape(
                topStart = 24.dp,
                topEnd = 24.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
                    .padding(
                        PaddingValues(
                            start = cardHorizontalPadding,
                            end = cardHorizontalPadding,
                            top = 64.dp,
                            bottom = 24.dp
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginHeader()

                Spacer(modifier = Modifier.height(28.dp))

                LoginForm(
                    uiState = uiState,
                    onCorreoChanged = { onEvent(LoginUiEvent.CorreoChanged(it)) },
                    onContrasenaChanged = { onEvent(LoginUiEvent.ContrasenaChanged(it)) },
                    onToggleMostrarContrasena = {
                        onEvent(LoginUiEvent.ToggleMostrarContrasena)
                    },
                    onRecordarSesionChanged = {
                        onEvent(LoginUiEvent.RecordarSesionChanged(it))
                    },
                    onOlvideContrasena = {
                        onEvent(LoginUiEvent.OlvideContrasenaClicked)
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                LoginActionButton(
                    isLoading = uiState.isLoading,
                    onClick = { onEvent(LoginUiEvent.IniciarSesionClicked)

                    }
                )

                Spacer(modifier = Modifier.height(spacing.large))

                Text(
                    text = "Version 1.0.0 - BAN 2026",
                    style = MaterialTheme.typography.labelSmall,
                    color = BanTextSecondary.copy(alpha = 0.65f),
                    textAlign = TextAlign.Center
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.banapp),
            contentDescription = "Logo BAN",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = cardTop - (logoSize / 2))
                .size(logoSize)
                .zIndex(2f)
        )
    }
}
