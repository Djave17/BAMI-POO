package com.example.recolectores.feature.perfil.data.dummy

import com.example.recolectores.feature.perfil.presentation.PerfilOpcionTipo
import com.example.recolectores.feature.perfil.presentation.PerfilOpcionUi
import com.example.recolectores.feature.perfil.presentation.PerfilUiState
import com.example.recolectores.feature.perfil.presentation.PerfilUsuarioUi

object PerfilDummyData {

    val usuario = PerfilUsuarioUi(
        nombreCompleto = "Carlos Mendoza",
        correoElectronico = "carlos.mendoza@bami.org",
        rol = "Recolector"
    )

    val opcionesConfiguracion = listOf(
        PerfilOpcionUi(
            tipo = PerfilOpcionTipo.Notificaciones,
            titulo = "Notificaciones"
        ),
        PerfilOpcionUi(
            tipo = PerfilOpcionTipo.AyudaSoporte,
            titulo = "Ayuda y Soporte"
        ),
        PerfilOpcionUi(
            tipo = PerfilOpcionTipo.AcercaDeBami,
            titulo = "Acerca de BAMI"
        )
    )

    val uiState = PerfilUiState(
        nombreCompleto = usuario.nombreCompleto,
        correoElectronico = usuario.correoElectronico,
        rol = usuario.rol,
        opciones = opcionesConfiguracion,
        appVersionText = "BAMI v1.0.0",
        appOwnerText = "© 2026 Banco de Alimentos"
    )
}
