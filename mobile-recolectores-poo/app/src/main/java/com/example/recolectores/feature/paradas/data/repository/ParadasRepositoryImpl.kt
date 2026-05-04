package com.example.recolectores.feature.paradas.data.repository

import com.example.recolectores.feature.paradas.data.mapper.ParadasMapper
import com.example.recolectores.feature.paradas.data.remote.dto.ParadasDetailDto
import com.example.recolectores.feature.paradas.domain.model.ParadasDetail
import com.example.recolectores.feature.paradas.domain.repository.ParadasRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

/**
 * Repositorio en memoria para el flujo de demo.
 *
 * Aunque hoy la fuente es dummy, la interfaz y el mapper ya quedan
 * alineados con una futura fuente remota. La UI solo observa dominio.
 */
class ParadasRepositoryImpl(
    seedStops: List<ParadasDetailDto>
) : ParadasRepository {

    private val stopsState = MutableStateFlow(
        seedStops
            .associateBy(ParadasDetailDto::paradaId)
            .mapValues { (_, dto) -> ParadasMapper.toDomain(dto) }
    )

    override fun observeStop(paradaId: String): Flow<ParadasDetail?> {
        return stopsState
            .map { stops -> stops[paradaId] }
            .distinctUntilChanged()
    }

    override suspend fun getStop(paradaId: String): ParadasDetail? {
        return stopsState.value[paradaId]
    }

    override suspend fun updateStop(
        paradaId: String,
        transform: (ParadasDetail) -> ParadasDetail
    ): ParadasDetail {
        var updatedStop: ParadasDetail? = null

        stopsState.update { currentStops ->
            val currentStop = requireNotNull(currentStops[paradaId]) {
                "No existe una parada con id=$paradaId."
            }

            val nextStop = transform(currentStop)
            updatedStop = nextStop

            currentStops + (paradaId to nextStop)
        }

        return requireNotNull(updatedStop)
    }
}
