package com.example.recolectores.core.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanBorder
import com.example.recolectores.core.designsystem.theme.BanGrayLight
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanSurface
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanYellow

enum class BanStatusChipVariant {
    WaitingDonation,
    Completed,
    Pending
}

@Immutable
private data class BanStatusChipColors(
    val containerColor: Color,
    val contentColor: Color,
    val borderColor: Color
)

@Composable
fun BanStatusChip(
    text: String,
    modifier: Modifier = Modifier,
    variant: BanStatusChipVariant = BanStatusChipVariant.Pending
) {
    val spacing = LocalBanSpacing

    val colors = when (variant) {
        BanStatusChipVariant.WaitingDonation -> BanStatusChipColors(
            containerColor = BanYellow,
            contentColor = BanTextPrimary,
            borderColor = BanYellow
        )

        BanStatusChipVariant.Completed -> BanStatusChipColors(
            containerColor = BanGreen,
            contentColor = BanSurface,
            borderColor = BanGreen
        )

        BanStatusChipVariant.Pending -> BanStatusChipColors(
            containerColor = BanGrayLight,
            contentColor = BanTextPrimary,
            borderColor = BanBorder
        )
    }

    Surface(
        modifier = modifier.heightIn(min = spacing.doubleExtraLarge),
        shape = CircleShape,
        color = colors.containerColor,
        contentColor = colors.contentColor,
        border = BorderStroke(
            width = spacing.border,
            color = colors.borderColor
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                PaddingValues(
                    horizontal = spacing.small,
                    vertical = spacing.extraSmall
                )
            ),
            style = MaterialTheme.typography.labelMedium,
            color = colors.contentColor
        )
    }
}