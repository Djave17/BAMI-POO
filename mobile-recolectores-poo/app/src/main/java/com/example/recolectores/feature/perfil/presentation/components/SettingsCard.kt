package com.example.recolectores.feature.perfil.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.theme.BanDivider
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.feature.perfil.presentation.PerfilOpcionTipo
import com.example.recolectores.feature.perfil.presentation.PerfilOpcionUi

/**
 * Tarjeta de configuracion.
 *
 * Las acciones se presentan como celdas suaves y familiares, sin iconos
 * accesorios, porque el diseno prioriza lectura directa sobre densidad visual.
 */
@Composable
fun SettingsCard(
    opciones: List<PerfilOpcionUi>,
    onOpcionClick: (PerfilOpcionTipo) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, BanDivider.copy(alpha = 0.45f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "Configuración",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                color = BanTextPrimary
            )

            opciones.forEach { opcion ->
                SettingsOptionButton(
                    titulo = opcion.titulo,
                    onClick = { onOpcionClick(opcion.tipo) }
                )
            }
        }
    }
}

@Composable
private fun SettingsOptionButton(
    titulo: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(0.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            border = BorderStroke(1.dp, BanDivider.copy(alpha = 0.55f))
        ) {
            Text(
                text = titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = BanTextPrimary
            )
        }
    }
}
