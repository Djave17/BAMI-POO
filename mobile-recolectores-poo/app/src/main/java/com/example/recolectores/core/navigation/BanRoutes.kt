package com.example.recolectores.core.navigation

import kotlinx.serialization.Serializable


/**
 * Rutas de navegación
 *
 * Propósito: Centralizar los identificadores de navegación usados por el grafo raíz, el shell autenticado y las pantallas internas.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Definir nombres estables para las rutas públicas de la aplicación.
 *   - Distinguir entre acceso inicial, contenedor autenticado y destinos internos como paradas o donaciones.
 *   - No contener lógica de navegación condicional ni reglas de negocio.
 * 
 *
 * Nota: `InicioSesion` pertenece al grafo raíz, `Main` representa el shell autenticado y el resto de rutas corresponden a pantallas internas del área operativa.
 */
object BanRoutes {
    const val ParadaIdArg = "paradaId"

    const val InicioSesion = "inicio_sesion"

    const val Main = "main"

    const val Inicio = "inicio"
    const val Rutas = "rutas"
    const val Perfil = "perfil"

    const val ParadaFlow = "parada_flow/{$ParadaIdArg}"
    const val DetalleParada = "detalle_parada/{$ParadaIdArg}"
    const val RegistrarDonacion = "registrar_donacion/{$ParadaIdArg}"
    const val EsperandoDonante = "esperando_donante/{$ParadaIdArg}"
    const val ParadaCompletada = "parada_completada/{$ParadaIdArg}"

    fun paradaFlow(paradaId: String): String = "parada_flow/$paradaId"

    fun detalleParada(paradaId: String): String = "detalle_parada/$paradaId"

    fun registrarDonacion(paradaId: String): String = "registrar_donacion/$paradaId"

    fun esperandoDonante(paradaId: String): String = "esperando_donante/$paradaId"

    fun paradaCompletada(paradaId: String): String = "parada_completada/$paradaId"
}
