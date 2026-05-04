package com.example.recolectores.feature.donaciones.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.components.BanButton

@Composable
fun SaveDonationButton(
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BanButton(
        text = "Guardar Donación",
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled
    )
}
