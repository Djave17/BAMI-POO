package com.example.recolectores.core.designsystem.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanSurface
import com.example.recolectores.core.designsystem.theme.BanTextPrimary

@Composable
fun BanButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fullWidth: Boolean = true,
    containerColor: Color = BanGreen,
    contentColor: Color = BanSurface,
    leadingIcon: @Composable (() -> Unit)? = null
){
    val spacing = LocalBanSpacing
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .then(
                if (fullWidth) {
                    Modifier.fillMaxWidth()
                } else {
                    Modifier
                }
            )
            .heightIn(min = 52.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor.copy(alpha = 0.45f),
            disabledContentColor = contentColor.copy(alpha = 0.80f)
        ),
        contentPadding = PaddingValues(
            horizontal = spacing.buttonHorizontalPadding,
            vertical = spacing.buttonVerticalPadding

        )
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.small)
        ) {
            leadingIcon?.invoke()
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun BanOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fullWidth: Boolean = true,
    textColor: Color,
    leadingIcon: @Composable (() -> Unit)? = null,
    containerColor: Color = BanSurface
) {
    val spacing = LocalBanSpacing

    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .then(
                if (fullWidth) Modifier.fillMaxWidth() else Modifier
            )
            .heightIn(min = spacing.buttonHeight),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.1.dp, textColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = textColor,
            disabledContainerColor = containerColor,
            disabledContentColor = BanTextPrimary.copy(alpha = 0.45f)
        ),
        contentPadding = PaddingValues(
            horizontal = spacing.buttonHorizontalPadding,
            vertical = spacing.buttonVerticalPadding
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.small)
        ) {
            leadingIcon?.invoke()
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
