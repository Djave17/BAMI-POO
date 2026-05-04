package com.example.recolectores.feature.autenticacion.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanTextSecondary

/**
 * Encabezado de login
 *
 * Propósito: Mostrar el contexto textual principal de la pantalla de acceso.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Presentar el título y la guía breve para iniciar sesión.
 *   - Mantener el contenido visual consistente con el resto del formulario.
 *   - No manejar navegación ni lógica de autenticación.
 * 
 */
@Composable
fun LoginHeader(
    modifier: Modifier = Modifier
) {
    val spacing = LocalBanSpacing

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Iniciar sesion",
            style = MaterialTheme.typography.titleLarge,
            color = BanTextPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(spacing.small))

        Text(
            text = "Ingresa tus credenciales para continuar",
            style = MaterialTheme.typography.bodyLarge,
            color = BanTextSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
