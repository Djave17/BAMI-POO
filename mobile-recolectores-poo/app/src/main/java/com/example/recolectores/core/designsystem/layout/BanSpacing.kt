package com.example.recolectores.core.designsystem.layout

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class BanSpacing(
    val none: Dp = 0.dp,
    val border: Dp = 1.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val large: Dp = 16.dp,
    val extraLarge: Dp = 20.dp,
    val doubleExtraLarge: Dp = 24.dp,
    val section: Dp = 32.dp,

    val buttonHeight: Dp = 48.dp,
    val buttonHorizontalPadding: Dp = 16.dp,
    val buttonVerticalPadding: Dp = 10.dp,

    val iconButtonSize: Dp = 40.dp,
    val topBarHeight: Dp = 56.dp,
    val textFieldHeight: Dp = 56.dp,

    val screenHorizontalPadding: Dp = 20.dp,
    val screenVerticalPadding: Dp = 16.dp,
    val headerHorizontalPadding: Dp = 20.dp,
    val headerVerticalPadding: Dp = 20.dp,

    val loginLogoSize: Dp = 64.dp,
    val loginLogoCompactSize: Dp = 64.dp,
    val loginLogoTopPadding: Dp = 56.dp,
    val loginLogoCompactTopPadding: Dp = 56.dp,
    val loginCardCornerRadius: Dp = 28.dp,
    val loginCardHorizontalPadding: Dp = 24.dp,
    val loginCardVerticalPadding: Dp = 24.dp,
    val loginFormTopSpace: Dp = 0.dp
)

val LocalBanSpacing = BanSpacing()