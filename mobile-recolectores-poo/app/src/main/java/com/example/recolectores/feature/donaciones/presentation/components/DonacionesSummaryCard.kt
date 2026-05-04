package com.example.recolectores.feature.donaciones.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanSurface

@Composable
fun DonacionesSummaryCard(
    totalWeightText: String,
    totalValueText: String,
    totalItemsText: String,
    modifier: Modifier = Modifier
) {
    val spacing = LocalBanSpacing

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            BanGreen,
                            BanGreen.copy(alpha = 0.88f)
                        )
                    ),
                    shape = MaterialTheme.shapes.large
                )
        ) {
            Column(
                modifier = Modifier.padding(spacing.doubleExtraLarge),
                verticalArrangement = Arrangement.spacedBy(spacing.large)
            ) {
                Text(
                    text = "Resumen Total",
                    style = MaterialTheme.typography.headlineMedium,
                    color = BanSurface
                )

                SummaryRow(label = "Total Peso:", value = totalWeightText)
                SummaryRow(label = "Total Valor:", value = totalValueText)
                SummaryRow(label = "Ítems:", value = totalItemsText)
            }
        }
    }
}

@Composable
private fun SummaryRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = BanSurface
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = BanSurface
        )
    }
}
