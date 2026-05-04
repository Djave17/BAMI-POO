
package com.example.recolectores.feature.perfil.presentation

/**
 * Estado de perfil
 *
 * Propósito: Reservar el estado visual del perfil, la sesión y la sincronización visible.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 *
 *   - Centralizar datos listos para pintar sin que la UI arme lógica extra.
 *   - Representar carga, contenido y posibles errores de perfil.
 *   - No hablar con infraestructura; este archivo es un placeholder o base inicial.
 *
 */
data class PerfilUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,

    val nombreCompleto: String = "",
    val correoElectronico: String = "",
    val rol: String = "",

    val opciones: List<PerfilOpcionUi> = emptyList(),

    val appVersionText: String = "",
    val appOwnerText: String = "© 2026 Banco de Alimentos",

    val isLogoutDialogVisible: Boolean = false,
    val isLogoutLoading: Boolean = false
)
data class PerfilUsuarioUi(
    val nombreCompleto: String = "",
    val correoElectronico: String = "",
    val rol: String = ""
) {
    val iniciales: String
        get() = nombreCompleto
            .split(" ")
            .filter { it.isNotBlank() }
            .take(2)
            .joinToString("") { it.first().uppercase() }
}

data class PerfilOpcionUi(
    val tipo: PerfilOpcionTipo,
    val titulo: String
) {
    companion object {
        fun defaultOptions(): List<PerfilOpcionUi> {
            return listOf(
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
        }
    }
}

enum class PerfilOpcionTipo {
    Notificaciones,
    AyudaSoporte,
    AcercaDeBami
}

sealed interface PerfilEvent {
    data object OnBackClick : PerfilEvent
    data object OnCerrarSesionClick : PerfilEvent
    data object OnConfirmarCerrarSesion : PerfilEvent
    data object OnCancelarCerrarSesion : PerfilEvent
    data class OnOpcionClick(val tipo: PerfilOpcionTipo) : PerfilEvent
}
