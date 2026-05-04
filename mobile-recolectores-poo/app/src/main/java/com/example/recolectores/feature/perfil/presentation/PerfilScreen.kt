package com.example.recolectores.feature.perfil.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.theme.BanBackground
import com.example.recolectores.core.designsystem.theme.BanDivider
import com.example.recolectores.core.designsystem.theme.BanSurface
import com.example.recolectores.feature.perfil.presentation.components.PerfilForm
import com.example.recolectores.feature.perfil.presentation.components.PerfilHeader

/**
 * Pantalla de perfil.
 *
 * La composicion replica la jerarquia visual del diseno aprobado:
 * una cabecera compacta, un bloque de identidad centrado y dos tarjetas
 * principales antes de la accion de cierre de sesion.
 */
@Composable
fun PerfilScreen(
    uiState: PerfilUiState,
    onOpcionClick: (PerfilOpcionTipo) -> Unit,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BanBackground)
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = BanSurface
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                PerfilHeader(
                    title = "Mi Perfil",
                    modifier = Modifier.padding(vertical = 18.dp)
                )
                HorizontalDivider(color = BanDivider.copy(alpha = 0.45f))
            }
        }

        when {
            uiState.isLoading -> {
                PerfilLoadingState(modifier = Modifier.weight(1f))
            }

            uiState.errorMessage != null -> {
                PerfilErrorState(
                    message = uiState.errorMessage,
                    modifier = Modifier.weight(1f)
                )
            }

            else -> {
                PerfilForm(
                    uiState = uiState,
                    onOpcionClick = onOpcionClick,
                    onLogoutClick = onLogoutClick,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )
            }
        }
    }
}

@Composable
private fun PerfilLoadingState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun PerfilErrorState(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
