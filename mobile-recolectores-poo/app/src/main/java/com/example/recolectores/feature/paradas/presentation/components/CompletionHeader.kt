package com.example.recolectores.feature.paradas.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.recolectores.core.designsystem.theme.BanTextSecondary

/**
 * Mensaje final de cierre de parada.
 */
@Composable
fun CompletionHeader(
    message: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = message,
        modifier = modifier.fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge,
        color = BanTextSecondary,
        textAlign = TextAlign.Center
    )
}
