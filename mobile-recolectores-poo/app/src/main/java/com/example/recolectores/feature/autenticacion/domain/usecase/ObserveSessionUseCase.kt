/**
 * Observación de sesión
 *
 * Propósito: Reservar el caso de uso que informará cambios del estado autenticado a presentation.
 *
 * Capa: domain.
 *
 * Responsabilidades:
 * 
 *   - Exponer una fuente estable para saber si la sesión sigue activa o debe renovarse.
 *   - Desacoplar a los ViewModels del origen técnico de la sesión.
 *   - No leer DataStore directo desde presentation; este archivo es un placeholder o base inicial.
 * 
 */
package com.example.recolectores.feature.autenticacion.domain.usecase
