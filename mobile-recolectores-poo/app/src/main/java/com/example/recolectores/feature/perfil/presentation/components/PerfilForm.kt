package com.example.recolectores.feature.perfil.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recolectores.feature.perfil.presentation.PerfilOpcionTipo
import com.example.recolectores.feature.perfil.presentation.PerfilUiState

/**
 * Cuerpo principal del perfil.
 *
 * Las secciones se ordenan igual que en la referencia para preservar
 * familiaridad: identidad, informacion personal, configuracion y sesion.
 */
@Composable
fun PerfilForm(
    uiState: PerfilUiState,
    onOpcionClick: (PerfilOpcionTipo) -> Unit,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        CollectorInfoCard(
            nombreCompleto = uiState.nombreCompleto,
            rol = uiState.rol
        )

        PersonalInfoCard(
            nombreCompleto = uiState.nombreCompleto,
            correoElectronico = uiState.correoElectronico,
            rol = uiState.rol
        )

        SettingsCard(
            opciones = uiState.opciones,
            onOpcionClick = onOpcionClick
        )

        PerfilSessionSection(
            appVersionText = uiState.appVersionText,
            appOwnerText = uiState.appOwnerText,
            isLogoutLoading = uiState.isLogoutLoading,
            onLogoutClick = onLogoutClick
        )
    }
}
