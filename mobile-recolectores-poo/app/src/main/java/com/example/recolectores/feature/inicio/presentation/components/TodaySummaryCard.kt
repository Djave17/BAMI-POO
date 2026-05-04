package com.example.recolectores.feature.inicio.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanYellow

/**
 * Tarjeta de resumen de la jornada.
 *
 * Muestra un dato importante del día de trabajo del recolector.
 *
 * Este componente pertenece a la capa presentation.
 *
 * Responsabilidades:
 * - Mostrar un título corto.
 * - Mostrar un valor principal.
 * - Mostrar un ícono relacionado con el dato.
 * - Reutilizar el mismo diseño para diferentes indicadores.
 */
@Composable
fun TodaySummaryCard(
    title: String,
    value: String,
    icon: ImageVector,
    containerColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(104.dp)
            .background(
                color = containerColor,
                shape = RoundedCornerShape(18.dp)
            )
            .padding(14.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(17.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }

        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
    }
}

/**
 * Fila de tarjetas de resumen.
 *
 * Agrupa los indicadores principales de la jornada en una misma sección.
 *
 * Este componente ayuda a que InicioScreen no tenga que repetir la estructura
 * visual de las tarjetas.
 */
@Composable
fun TodaySummaryCardsRow(
    totalRecolectado: String,
    montoEstimadoDonacion: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TodaySummaryCard(
            title = "Total recolectado",
            value = totalRecolectado,
            icon = Icons.Outlined.Inventory2,
            containerColor = BanYellow,
            modifier = Modifier.weight(1f)
        )

        TodaySummaryCard(
            title = "Monto Estimado",
            value = "C$" + montoEstimadoDonacion,
            icon = Icons.Outlined.Money,
            containerColor = BanGreen,
            modifier = Modifier.weight(1f)
        )
    }
}
