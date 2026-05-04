package com.example.recolectores.core.designsystem.states



data class RouteProgressUiState(
    val completedStops: Int = 0,
    val totalStops: Int = 0
) {
    val progress: Float
        get() = if (totalStops <= 0) {
            0f
        } else {
            (completedStops.toFloat() / totalStops.toFloat()).coerceIn(0f, 1f)
        }

    val progressText: String
        get() = "$completedStops de $totalStops paradas"
}