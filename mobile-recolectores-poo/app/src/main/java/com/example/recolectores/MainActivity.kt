package com.example.recolectores

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.recolectores.core.designsystem.theme.BanTheme
import com.example.recolectores.core.navigation.BanNavHost

/**
 * Actividad raíz de BAN
 *
 * Propósito: Alojar la jerarquía principal de Compose y abrir el grafo raíz de la aplicación.
 *
 * Capa: presentation.
 *
 * Responsabilidades:
 * 
 *   - Inicializar el tema visual y el contenedor principal de navegación.
 *   - Servir como punto de entrada Android para las pantallas de recolectores y rutas.
 *   - No ejecutar reglas de negocio ni acceder directo a API, Room, DataStore o WebSocket.
 * 
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            )
        )
        setContent {
            BanTheme {
                BanNavHost()
            }
        }
    }
}
