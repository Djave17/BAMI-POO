package com.example.recolectores.feature.donaciones.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.theme.BanBorder
import com.example.recolectores.core.designsystem.theme.BanError
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanSurface
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanTextSecondary
import com.example.recolectores.feature.donaciones.presentation.DonationCategoryOptionUiState

/**
 * Dropdown de categoría reutilizable para el formulario de donaciones.
 *
 * Permite usar label interna cuando el layout la necesite y ocultarla
 * cuando la composición externa ya dibuja la etiqueta por separado.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdown(
    categories: List<DonationCategoryOptionUiState>,
    selectedCategoryId: String?,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorText: String? = null,
    labelText: String? = "Categoría"
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedText = categories.firstOrNull { it.id == selectedCategoryId }?.label.orEmpty()

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {},
            modifier = Modifier
                .menuAnchor(
                    type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                    enabled = true
                )
                .clickable { expanded = true },
            readOnly = true,
            singleLine = true,
            label = labelText?.let {
                {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            shape = MaterialTheme.shapes.medium,
            isError = errorText != null,
            supportingText = errorText?.let {
                {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = BanTextPrimary,
                unfocusedTextColor = BanTextPrimary,
                focusedContainerColor = BanSurface,
                unfocusedContainerColor = BanSurface,
                focusedBorderColor = BanGreen,
                unfocusedBorderColor = BanBorder,
                errorBorderColor = BanError,
                focusedLabelColor = BanGreen,
                unfocusedLabelColor = BanTextSecondary,
                cursorColor = BanGreen
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(text = category.label) },
                    onClick = {
                        expanded = false
                        onCategorySelected(category.id)
                    }
                )
            }
        }
    }
}
