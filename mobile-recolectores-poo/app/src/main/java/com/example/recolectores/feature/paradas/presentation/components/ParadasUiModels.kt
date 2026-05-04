package com.example.recolectores.feature.paradas.presentation.components

/**
 * Intensidad visual del badge de estado.
 *
 * Se separa del dominio para que las pantallas puedan evolucionar su
 * apariencia sin alterar las reglas de negocio ni los enum de backend.
 */
enum class ParadaStatusTone {
    Neutral,
    Warning,
    Success,
    Danger
}

/**
 * Estado listo para renderizar la tarjeta compartida de información.
 */
data class ParadasInfoCardUiState(
    val title: String = "Información de la Parada",
    val donorLabel: String = "Donante",
    val donorName: String = "",
    val branchLabel: String = "Sucursal",
    val branchName: String = "",
    val addressLabel: String = "Dirección",
    val addressLine: String = "",
    val scheduledArrivalLabel: String = "Hora Estimada",
    val scheduledArrivalTimeText: String = "",
    val arrivedAtLabel: String = "Llegada registrada",
    val arrivedAtTimeText: String? = null
)

/**
 * Estado visual del temporizador de espera.
 */
data class WaitingTimerCardUiState(
    val title: String = "Tiempo de Espera",
    val statusText: String = "",
    val timerText: String = "",
    val timerCaption: String = "",
    val limitText: String = "",
    val remainingText: String = "",
    val helperText: String = "",
    val progress: Float = 0f,
    val isExpired: Boolean = false
)
