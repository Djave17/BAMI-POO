/**
 * Cierre de sesión desde perfil
 *
 * Propósito: Reservar el caso de uso que cerrará la sesión desde la sección de perfil.
 *
 * Capa: domain.
 *
 * Responsabilidades:
 * 
 *   - Desconectar al recolector y limpiar estado persistido cuando se implemente.
 *   - Evitar que la UI conozca los detalles de la salida de sesión.
 *   - No manipular infraestructura desde presentation; este archivo es un placeholder o base inicial.
 * 
 */
package com.example.recolectores.feature.perfil.domain.usecase

/**
 * Caso de uso para cerrar sesion.
 *
 * La limpieza real de token, DataStore o caches se conecta aqui sin exponer
 * detalles de infraestructura a la capa presentation.
 */
class LogoutUseCase(
    private val clearSession: suspend () -> Unit = {}
) {
    suspend operator fun invoke() {
        clearSession()
    }
}
