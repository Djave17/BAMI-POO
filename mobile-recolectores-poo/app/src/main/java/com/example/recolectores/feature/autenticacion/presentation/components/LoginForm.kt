package com.example.recolectores.feature.autenticacion.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.components.BanTextField
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanSurface
import com.example.recolectores.core.designsystem.theme.BanTextSecondary
import com.example.recolectores.feature.autenticacion.presentation.LoginUiState

/**
 * Formulario de acceso
 *
 * Propósito: Agrupar los campos y acciones principales del login en un bloque reutilizable.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Mostrar entradas del formulario con el estado y errores ya resueltos.
 *   - Emitir callbacks simples para que la pantalla o el ViewModel manejen los eventos.
 *   - No validar negocio ni acceder directo a API, Room, DataStore o WebSocket.
 * 
 */
@Composable
fun LoginForm(
    uiState: LoginUiState,
    onCorreoChanged: (String) -> Unit,
    onContrasenaChanged: (String) -> Unit,
    onToggleMostrarContrasena: () -> Unit,
    onRecordarSesionChanged: (Boolean) -> Unit,
    onOlvideContrasena: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalBanSpacing

    Column(modifier = modifier.fillMaxWidth()) {
        BanTextField(
            value = uiState.correo,
            onValueChange = onCorreoChanged,
            placeholder = "tu@email.com",
            isError = uiState.correoError != null,
            supportingText = uiState.correoError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(spacing.large))

        BanTextField(
            value = uiState.contrasena,
            onValueChange = onContrasenaChanged,
            placeholder = "********",
            isError = uiState.contrasenaError != null,
            supportingText = uiState.contrasenaError,
            visualTransformation = if (uiState.mostrarContrasena) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = onToggleMostrarContrasena) {
                    Icon(
                        imageVector = if (uiState.mostrarContrasena) {
                            Icons.Filled.VisibilityOff
                        } else {
                            Icons.Filled.Visibility
                        },
                        contentDescription = if (uiState.mostrarContrasena) {
                            "Ocultar contrasena"
                        } else {
                            "Mostrar contrasena"
                        },
                        tint = BanTextSecondary
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(spacing.large))

        RememberSessionRow(
            checked = uiState.recordarSesion,
            onCheckedChange = onRecordarSesionChanged
        )

        Spacer(modifier = Modifier.height(spacing.medium))

        ForgotPasswordText(onClick = onOlvideContrasena)
    }
}

@Composable
private fun RememberSessionRow(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CompositionLocalProvider(
            LocalMinimumInteractiveComponentSize provides Dp.Unspecified
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.size(18.dp),
                colors = CheckboxDefaults.colors(
                    checkedColor = BanGreen,
                    uncheckedColor = BanTextSecondary.copy(alpha = 0.65f),
                    checkmarkColor = BanSurface
                )
            )
        }

        Text(
            text = "Recordar sesion",
            style = MaterialTheme.typography.bodyMedium,
            color = BanGreen
        )
    }
}

@Composable
private fun ForgotPasswordText(
    onClick: () -> Unit
) {
    Text(
        text = buildAnnotatedString {
            append("Olvide mi contrasena. ")
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = BanGreen
                )
            ) {
                append("Que hago?")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        style = MaterialTheme.typography.bodyMedium,
        color = BanGreen
    )
}
