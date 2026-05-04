package com.example.recolectores.feature.perfil.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.recolectores.core.designsystem.theme.BanTextPrimary

/**
 * Cabecera superior de la pantalla.
 *
 * Se mantiene deliberadamente simple para respetar el patron del diseno:
 * un titulo centrado sobre fondo blanco, sin acciones laterales visibles.
 */
@Composable
fun PerfilHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        modifier = modifier.fillMaxWidth(),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = BanTextPrimary,
        textAlign = TextAlign.Center
    )
}
