package com.example.recolectores.feature.inicio.presentation.components



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Person3
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.theme.BanGreen
import com.example.recolectores.core.designsystem.theme.BanSurface
import com.example.recolectores.core.designsystem.theme.BanTextPrimary
import com.example.recolectores.core.designsystem.theme.BanTextSecondary

//Debe mostrar información del recolector y la fecha de la jornada.
// No debe saber de login, sesión, API, Room ni ViewModel.

/**
 * Encabezado de la pantalla de inicio.
 *
 * Muestra el saludo principal del recolector, su rol y la fecha de la jornada.
 *
 * Este componente pertenece a la capa presentation.
 *
 * Responsabilidades:
 * - Mostrar el nombre del recolector.
 * - Mostrar el rol del usuario.
 * - Mostrar la fecha de trabajo.
 * - No manejar sesión, navegación ni carga de datos.
 */
@Composable
fun InicioHeader(
    recolectorNombre: String,
    recolectorRol: String,
    fechaJornada: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BanSurface),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            HeaderIcon()

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "Hola, $recolectorNombre",
                    style = MaterialTheme.typography.titleLarge,
                    color = BanTextPrimary
                )

                Text(
                    text = recolectorRol,
                    style = MaterialTheme.typography.bodyMedium,
                    color = BanTextSecondary
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.CalendarToday,
                contentDescription = null,
                tint = BanTextSecondary,
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = fechaJornada,
                style = MaterialTheme.typography.bodyMedium,
                color = BanTextSecondary
            )
        }
    }
}

/**
 * Ícono principal del encabezado.
 *
 * Se separa en una función privada porque solo pertenece al diseño interno
 * de InicioHeader y no debe reutilizarse fuera de este archivo.
 */
@Composable
private fun HeaderIcon() {
    Box(
        modifier = Modifier
            .size(44.dp)
            .background(
                color = BanGreen,
                shape = RoundedCornerShape(12.dp)
            )
            ,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Person3,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(26.dp)
        )
    }
}
