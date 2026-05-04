package com.example.recolectores.feature.paradas.domain.repository

import com.example.recolectores.feature.paradas.domain.model.ParadasDetail
import kotlinx.coroutines.flow.Flow

/**
 * Contrato del flujo de paradas.
 *
 * La presentación observa una parada concreta y solicita mutaciones
 * expresivas; el repositorio decide después si eso viaja a memoria,
 * base local, API o sincronización offline.
 */
interface ParadasRepository {

    fun observeStop(paradaId: String): Flow<ParadasDetail?>

    suspend fun getStop(paradaId: String): ParadasDetail?

    suspend fun updateStop(
        paradaId: String,
        transform: (ParadasDetail) -> ParadasDetail
    ): ParadasDetail
}
