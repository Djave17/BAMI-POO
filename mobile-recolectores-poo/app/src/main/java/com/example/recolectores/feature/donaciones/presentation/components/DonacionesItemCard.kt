package com.example.recolectores.feature.donaciones.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.components.BanSurfaceCard
import com.example.recolectores.core.designsystem.components.BanTextField
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.feature.donaciones.presentation.DonationCategoryOptionUiState
import com.example.recolectores.feature.donaciones.presentation.DonationFormItemUiState

/**
 * Tarjeta de captura de información de un ítem de donación.
 *
 * Replica el layout del componente visual acordado:
 * - título corto
 * - categoría con label externa
 * - peso y valor en dos columnas paralelas
 */
@Composable
fun DonacionesItemCard(
    uiState: DonationFormItemUiState,
    categories: List<DonationCategoryOptionUiState>,
    onCategorySelected: (String) -> Unit,
    onWeightChange: (String) -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalBanSpacing

    BanSurfaceCard(
        modifier = modifier,
        contentPadding = PaddingValues(spacing.large)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.medium)
        ) {
            Text(
                text = uiState.title,
                style = MaterialTheme.typography.titleMedium,
                color = BanTextPrimary
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.small)
            ) {
                DonationFieldLabel(text = "Categoría")
                CategoryDropdown(
                    categories = categories,
                    selectedCategoryId = uiState.selectedCategoryId,
                    onCategorySelected = onCategorySelected,
                    errorText = uiState.categoryError,
                    labelText = null
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.48f),
                    verticalArrangement = Arrangement.spacedBy(spacing.small)
                ) {
                    DonationFieldLabel(text = "Peso (kg)")
                    DonationNumberField(
                        value = uiState.weightInput,
                        onValueChange = onWeightChange,
                        errorText = uiState.weightError
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(0.48f),
                    verticalArrangement = Arrangement.spacedBy(spacing.small)
                ) {
                    DonationFieldLabel(text = "Valor ($)")
                    DonationNumberField(
                        value = uiState.valueInput,
                        onValueChange = onValueChange,
                        errorText = uiState.valueError
                    )
                }
            }
        }
    }
}

@Composable
private fun DonationNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorText: String? = null
) {
    BanTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = "",
        modifier = modifier,
        isError = errorText != null,
        supportingText = errorText
    )
}

@Composable
private fun DonationFieldLabel(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = BanTextPrimary
    )
}
