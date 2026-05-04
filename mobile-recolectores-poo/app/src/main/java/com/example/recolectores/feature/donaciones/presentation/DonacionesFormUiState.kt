package com.example.recolectores.feature.donaciones.presentation

/**
 * Estado de presentación del formulario de donaciones.
 */
data class DonacionesFormUiState(
    val title: String = "Registrar Donación",
    val donorName: String = "",
    val branchName: String = "",
    val categories: List<DonationCategoryOptionUiState> = emptyList(),
    val items: List<DonationFormItemUiState> = emptyList(),
    val totalWeightText: String = "0.00 kg",
    val totalValueText: String = "$0.00",
    val totalItemsText: String = "0",
    val isSaveEnabled: Boolean = false
)

data class DonationCategoryOptionUiState(
    val id: String,
    val label: String
)

data class DonationFormItemUiState(
    val id: String,
    val title: String,
    val selectedCategoryId: String?,
    val selectedCategoryText: String,
    val weightInput: String,
    val valueInput: String,
    val categoryError: String? = null,
    val weightError: String? = null,
    val valueError: String? = null
)
