/**
 * Sincronizar datos pendientes
 *
 * Propósito: Reservar el caso de uso que enviará al servidor los cambios guardados sin conexión.
 *
 * Capa: domain.
 *
 * Responsabilidades:
 * 
 *   - Coordinar el despacho de datos pendientes desde la cola local.
 *   - Usar repositorios para sincronizar sin exponer detalles técnicos a presentation.
 *   - No reemplazar REST con WebSocket; este archivo es un placeholder o base inicial.
 * 
 *
 * Nota: WebSocket ayuda a informar cambios, pero la sincronización de escritura sigue pasando por el flujo normal de repositorios y servicios remotos.
 */
package com.example.recolectores.feature.sincronizacion.domain.usecase
