package com.example.recolectores.feature.rutas.presentation

import androidx.lifecycle.ViewModel
import com.example.recolectores.feature.rutas.data.dummy.RutasDummyData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel de rutas.
 *
 * Se mantiene intencionalmente simple mientras la fuente real de rutas
 * todavia no esta integrada; asi la pantalla conserva un contrato estable.
 */
class RutasViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RutasDummyData.initialState)
    val uiState: StateFlow<RutasUiState> = _uiState.asStateFlow()
}
