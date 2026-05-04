package com.example.recolectores.core.appinfo

/**
 * Contrato minimo para exponer metadatos visibles de la aplicacion.
 *
 * Mantener esta abstraccion evita acoplar la capa de presentation
 * a detalles de Android o a constantes generadas de compilacion.
 */
interface AppInfoProvider {
    fun getVersionName(): String
}
