package com.example.recolectores.feature.donaciones.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recolectores.core.designsystem.components.BanOutlinedButton
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.feature.donaciones.presentation.components.DonacionesHeader
import com.example.recolectores.feature.donaciones.presentation.components.DonacionesItemCard
import com.example.recolectores.feature.donaciones.presentation.components.DonacionesSummaryCard
import com.example.recolectores.feature.donaciones.presentation.components.SaveDonationButton
import com.example.recolectores.feature.paradas.presentation.components.ParadaFlowScreenScaffold
import com.example.recolectores.feature.paradas.presentation.components.ParadaTopBar

/**
 * Formulario de registro de donación dentro del flujo de parada.
 */
@Composable
fun DonacionesFormScreen(
    uiState: DonacionesFormUiState,
    onBackClick: () -> Unit,
    onCategorySelected: (itemId: String, categoryId: String) -> Unit,
    onWeightChange: (itemId: String, value: String) -> Unit,
    onValueChange: (itemId: String, value: String) -> Unit,
    onAddItemClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ParadaFlowScreenScaffold(
        modifier = modifier,
        topBar = {
            ParadaTopBar(
                title = uiState.title,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            SaveDonationButton(
                isEnabled = uiState.isSaveEnabled,
                onClick = onSaveClick
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(LocalBanSpacing.large)
        ) {
            DonacionesHeader(
                donorName = uiState.donorName,
                branchName = uiState.branchName
            )

            Text(
                text = "Detalle de Donación",
                style = MaterialTheme.typography.headlineMedium
            )

            uiState.items.forEach { item ->
                DonacionesItemCard(
                    uiState = item,
                    categories = uiState.categories,
                    onCategorySelected = { categoryId ->
                        onCategorySelected(item.id, categoryId)
                    },
                    onWeightChange = { value ->
                        onWeightChange(item.id, value)
                    },
                    onValueChange = { value ->
                        onValueChange(item.id, value)
                    }
                )
            }

            BanOutlinedButton(
                text = "Agregar otro ítem",
                onClick = onAddItemClick,
                textColor = BanGreen,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null
                    )
                }
            )

            DonacionesSummaryCard(
                totalWeightText = uiState.totalWeightText,
                totalValueText = uiState.totalValueText,
                totalItemsText = uiState.totalItemsText
            )
        }
    }
}
