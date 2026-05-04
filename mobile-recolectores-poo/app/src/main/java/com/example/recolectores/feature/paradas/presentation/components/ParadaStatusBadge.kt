package com.example.recolectores.feature.paradas.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.theme.BanDanger
import com.example.recolectores.core.designsystem.theme.BanGrayLight
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanSurface
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanYellow

@Composable
fun ParadaStatusBadge(
    label: String,
    tone: ParadaStatusTone,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, contentColor) = tone.colors()

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = MaterialTheme.shapes.extraLarge
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = contentColor,
            textAlign = TextAlign.Center
        )
    }
}

private fun ParadaStatusTone.colors(): Pair<Color, Color> {
    return when (this) {
        ParadaStatusTone.Neutral -> BanGrayLight to BanTextPrimary
        ParadaStatusTone.Warning -> BanYellow to BanTextPrimary
        ParadaStatusTone.Success -> BanGreen to BanSurface
        ParadaStatusTone.Danger -> BanDanger.copy(alpha = 0.14f) to BanDanger
    }
}
