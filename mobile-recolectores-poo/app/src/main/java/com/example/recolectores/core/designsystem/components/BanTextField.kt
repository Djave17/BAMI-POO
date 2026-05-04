package com.example.recolectores.core.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.VisualTransformation
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanBorder
import com.example.recolectores.core.designsystem.theme.BanError
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanSurface
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanTextSecondary

@Composable
fun BanTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val spacing = LocalBanSpacing

    OutlinedTextField(
        trailingIcon = trailingIcon,
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = spacing.textFieldHeight),
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        minLines = minLines,
        maxLines = maxLines,
        isError = isError,
        label = label?.let {
            {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium,
                color = BanTextSecondary
            )
        },
        supportingText = supportingText?.let {
            {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        shape = MaterialTheme.shapes.medium,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = BanTextPrimary,
            unfocusedTextColor = BanTextPrimary,
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            disabledContainerColor = BanSurface,
            focusedBorderColor = BanGreen,
            unfocusedBorderColor = BanBorder,
            errorBorderColor = BanError,
            focusedLabelColor = BanGreen,
            unfocusedLabelColor = BanTextSecondary,
            errorLabelColor = BanError,
            cursorColor = BanGreen,
            errorCursorColor = BanError
        )
    )
}
