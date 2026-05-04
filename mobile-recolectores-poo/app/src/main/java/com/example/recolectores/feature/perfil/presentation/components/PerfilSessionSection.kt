package com.example.recolectores.feature.perfil.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.theme.BanTextDisabled

/**
 * Acciones y metadatos de sesion.
 *
 * Esta seccion delega el CTA principal a `LogoutButton` para reutilizar
 * el estilo outlined del design system en lugar de mantener un boton inline.
 */
@Composable
fun PerfilSessionSection(
    appVersionText: String,
    appOwnerText: String,
    isLogoutLoading: Boolean,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        LogoutButton(
            isLoading = isLogoutLoading,
            onClick = onLogoutClick,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = appVersionText,
                style = MaterialTheme.typography.bodySmall,
                color = BanTextDisabled
            )
            Text(
                text = appOwnerText,
                style = MaterialTheme.typography.bodySmall,
                color = BanTextDisabled
            )
        }
    }
}
