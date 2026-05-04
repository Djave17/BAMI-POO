package com.example.recolectores.feature.paradas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recolectores.feature.paradas.domain.repository.ParadasRepository
import java.time.Clock
import java.time.ZoneId

/**
 * Factory manual del flujo de paradas.
 *
 * Mantiene el mismo enfoque del resto del proyecto: wiring explícito
 * sin introducir Hilt mientras la aplicación aún trabaja con dummies
 * y dependencias sencillas.
 */
class ParadaFlowViewModelFactory(
    private val paradaId: String,
    private val repository: ParadasRepository,
    private val clock: Clock,
    private val zoneId: ZoneId
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(ParadaFlowViewModel::class.java)) {
            "ParadaFlowViewModelFactory solo puede crear ParadaFlowViewModel."
        }

        return ParadaFlowViewModel(
            paradaId = paradaId,
            repository = repository,
            clock = clock,
            zoneId = zoneId
        ) as T
    }
}
