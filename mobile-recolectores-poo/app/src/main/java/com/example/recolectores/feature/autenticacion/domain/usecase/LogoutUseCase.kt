/**
 * Cierre de sesión
 *
 * Propósito: Reservar el caso de uso que limpiará el estado autenticado del recolector.
 *
 * Capa: domain.
 *
 * Responsabilidades:
 * 
 *   - Centralizar la salida segura de sesión y la limpieza local cuando se implemente.
 *   - Evitar que presentation conozca cómo se borra el estado persistido.
 *   - No manipular DataStore o red desde la UI; este archivo es un placeholder o base inicial.
 * 
 */
package com.example.recolectores.feature.autenticacion.domain.usecase
