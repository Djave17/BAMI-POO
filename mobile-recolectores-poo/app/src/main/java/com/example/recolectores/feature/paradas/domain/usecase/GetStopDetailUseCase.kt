package com.example.recolectores.feature.paradas.domain.usecase

import com.example.recolectores.feature.paradas.domain.model.ParadasDetail
import com.example.recolectores.feature.paradas.domain.repository.ParadasRepository
import kotlinx.coroutines.flow.Flow

/**
 * Expone una parada observable para la capa de presentación.
 */
class GetStopDetailUseCase(
    private val repository: ParadasRepository
) {
    operator fun invoke(paradaId: String): Flow<ParadasDetail?> {
        return repository.observeStop(paradaId)
    }
}
