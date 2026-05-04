package com.example.recolectores.feature.rutas.presentation

import com.example.recolectores.feature.rutas.data.dummy.RutasDummyData
import org.junit.Assert.assertEquals
import org.junit.Test

class RutasViewModelTest {

    @Test
    fun inicia_con_la_ruta_dummy_configurada() {
        val viewModel = RutasViewModel()

        assertEquals(RutasDummyData.initialState, viewModel.uiState.value)
    }
}
