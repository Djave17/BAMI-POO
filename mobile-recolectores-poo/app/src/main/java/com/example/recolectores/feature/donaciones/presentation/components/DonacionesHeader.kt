package com.example.recolectores.feature.donaciones.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.components.BanSurfaceCard
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanTextSecondary

@Composable
fun DonacionesHeader(
    donorName: String,
    branchName: String,
    modifier: Modifier = Modifier
) {
    BanSurfaceCard(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalBanSpacing.large)
        ) {
            DonationInfoBlock(
                label = "Donante",
                value = donorName
            )
            DonationInfoBlock(
                label = "Sucursal",
                value = branchName
            )
        }
    }
}

@Composable
private fun DonationInfoBlock(
    label: String,
    value: String
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = BanTextSecondary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = BanTextPrimary
        )
    }
}
