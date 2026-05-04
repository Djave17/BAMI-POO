/**
 * Fuente de datos WebSocket
 *
 * Propósito: Reservar la capa de acceso que adaptará el cliente WebSocket a un contrato consumible por repositorios.
 *
 * Capa: data.
 *
 * Responsabilidades:
 * 
 *   - Emitir eventos de operaciones en un formato estable para la capa data.
 *   - Separar el detalle del cliente técnico de los repositorios.
 *   - No ser accedido desde presentation; este archivo es un placeholder o base inicial.
 * 
 *
 * Nota: WebSocket complementa REST para eventos en vivo, pero la persistencia y consulta principal siguen pasando por repositorios y servicios remotos.
 */
package com.example.recolectores.feature.sincronizacion.data.websocket
