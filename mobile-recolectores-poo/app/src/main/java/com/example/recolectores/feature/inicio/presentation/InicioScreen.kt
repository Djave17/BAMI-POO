package com.example.recolectores.feature.inicio.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanBackground
import com.example.recolectores.core.designsystem.theme.BanSurface
import com.example.recolectores.feature.inicio.presentation.components.InicioHeader
import com.example.recolectores.feature.inicio.presentation.components.NextStopCard
import com.example.recolectores.feature.inicio.presentation.components.OperationalRequestCard
import com.example.recolectores.feature.inicio.presentation.components.RouteProgressCard
import com.example.recolectores.feature.inicio.presentation.components.TodaySummaryCardsRow

/**
 * Pantalla de inicio del recolector.
 *
 * Muestra el resumen principal de la jornada, el avance de la ruta,
 * la próxima parada y las solicitudes operativas recibidas.
 *
 * Esta pantalla pertenece a la capa presentation.
 *
 * Responsabilidades:
 * - Ordenar los componentes principales del Home.
 * - Mostrar el estado recibido desde InicioUiState.
 * - Enviar acciones del usuario hacia afuera mediante callbacks.
 * - No cargar datos directamente desde API, Room, DataStore ni WebSocket.
 */
@Composable
fun InicioScreen(
    uiState: InicioUiState,
    onAcceptOperationalRequest: (String) -> Unit,
    onDenyOperationalRequest: (String) -> Unit,
    onNextStopClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalBanSpacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BanBackground)
            .verticalScroll(rememberScrollState())
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("inicio_header_surface"),
            color = BanSurface
        ) {
            InicioHeader(
                recolectorNombre = uiState.recolectorNombre,
                recolectorRol = uiState.recolectorRol,
                fechaJornada = uiState.fechaJornada,
                modifier = Modifier.padding(
                    horizontal = spacing.headerHorizontalPadding,
                    vertical = spacing.headerVerticalPadding
                )
            )
        }

        Column(
            modifier = Modifier.padding(
                start = spacing.headerHorizontalPadding,
                end = spacing.headerHorizontalPadding,
                top = 22.dp,
                bottom = spacing.headerVerticalPadding
            ),
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            RouteProgressCard(
                progressState = uiState.routeProgress
            )

            TodaySummaryCardsRow(
                totalRecolectado = uiState.totalRecolectado,
                montoEstimadoDonacion = uiState.montoEstimadoDonacion
            )

            NextStopSection(
                nextStop = uiState.proximaParada,
                onNextStopClick = onNextStopClick
            )

            OperationalRequestsSection(
                requests = uiState.solicitudesOperativas,
                onAccept = onAcceptOperationalRequest,
                onDeny = onDenyOperationalRequest
            )
        }
    }
}

/**
 * Sección de próxima parada.
 *
 * Muestra la parada que el recolector debe atender.
 * Si no hay una próxima parada, la sección no se muestra.
 */
@Composable
private fun NextStopSection(
    nextStop: NextStopUiState?,
    onNextStopClick: (String) -> Unit
) {
    if (nextStop == null) return

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SectionTitle(
            title = "Próxima parada",
            subtitle = "Completa las paradas en orden"
        )

        NextStopCard(
            nextStop = nextStop,
            onClick = {
                onNextStopClick(nextStop.id)
            }
        )
    }
}

/**
 * Sección de solicitudes operativas.
 *
 * Muestra las paradas sugeridas o enviadas por Operaciones.
 * Estas solicitudes pueden venir de WebSocket más adelante, pero aquí
 * solo se reciben como estado preparado para la UI.
 */
@Composable
private fun OperationalRequestsSection(
    requests: List<OperationalRequestUiState>,
    onAccept: (String) -> Unit,
    onDeny: (String) -> Unit
) {
    if (requests.isEmpty()) return

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SectionTitle(
            title = "Solicitud de parada",
            subtitle = "Revisa las paradas enviadas por Operaciones"
        )

        requests.forEach { request ->
            OperationalRequestCard(
                request = request,
                onAccept = {
                    onAccept(request.id)
                },
                onDeny = {
                    onDeny(request.id)
                }
            )
        }
    }
}

/**
 * Título reutilizable para secciones del Home.
 *
 * Mantiene el mismo estilo para bloques como próxima parada
 * y solicitud de parada.
 */
@Composable
private fun SectionTitle(
    title: String,
    subtitle: String
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF1F2937),
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF6B7280)
        )
    }
}
