package com.example.recolectores.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val BanLightColorScheme = lightColorScheme(
    primary = BanGreen,
    onPrimary = Color.White,

    secondary = BanGold,
    onSecondary = BanTextPrimary,

    tertiary = BanOrange,
    onTertiary = BanTextPrimary,

    background = BanBackground,
    onBackground = BanTextPrimary,

    surface = BanSurface,
    onSurface = BanTextPrimary,

    surfaceVariant = BanGrayLight,
    onSurfaceVariant = BanTextSecondary,

    outline = BanBorder,
    error = BanError,
    onError = Color.White
)

@Composable
fun BanTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = BanLightColorScheme,
        typography = BanTypography,
        shapes = BanShapes,
        content = content
    )
}