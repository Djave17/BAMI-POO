package com.example.recolectores.feature.paradas.presentation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.recolectores.core.navigation.BanRoutes
import com.example.recolectores.feature.donaciones.presentation.DonacionesFormScreen
import com.example.recolectores.feature.paradas.domain.model.ParadasStatus
import com.example.recolectores.feature.paradas.domain.repository.ParadasRepository
import com.example.recolectores.feature.paradas.presentation.completed.ParadasCompletedScreen
import com.example.recolectores.feature.paradas.presentation.detail.ParadasDetailScreen
import com.example.recolectores.feature.paradas.presentation.waiting.WaitingDonorScreen
import java.time.Clock
import java.time.ZoneId

/**
 * Registro de destinos del flujo de paradas.
 *
 * Se usa una ruta de entrada (`parada_flow/{paradaId}`) como dueña del
 * `ParadaFlowViewModel`; luego cada pantalla concreta reutiliza esa misma
 * instancia para conservar observaciones, timer y borrador de donación.
 */
fun NavGraphBuilder.paradaFlowDestinations(
    navController: NavHostController,
    repository: ParadasRepository,
    clock: Clock,
    zoneId: ZoneId,
    onReportIncident: (String) -> Unit = {},
    onCancelStop: (String) -> Unit = {}
) {
    composable(route = BanRoutes.ParadaFlow) { backStackEntry ->
        val paradaId = requireNotNull(backStackEntry.arguments?.getString(BanRoutes.ParadaIdArg))
        val viewModel = rememberParadaFlowViewModel(
            navController = navController,
            paradaId = paradaId,
            repository = repository,
            clock = clock,
            zoneId = zoneId
        )
        val uiState by viewModel.uiState.collectAsState()

        HandleParadaFlowCallbacks(
            uiState = uiState,
            viewModel = viewModel,
            onReportIncident = onReportIncident,
            onCancelStop = onCancelStop
        )

        LaunchedEffect(uiState.parada?.status) {
            val targetRoute = when (uiState.parada?.status) {
                ParadasStatus.Pending -> BanRoutes.detalleParada(paradaId)
                ParadasStatus.WaitingDonation -> BanRoutes.esperandoDonante(paradaId)
                ParadasStatus.Completed -> BanRoutes.paradaCompletada(paradaId)
                ParadasStatus.Cancelled -> BanRoutes.detalleParada(paradaId)
                null -> null
            }

            if (targetRoute != null) {
                navController.navigate(targetRoute) {
                    launchSingleTop = true
                }
            }
        }
    }

    composable(route = BanRoutes.DetalleParada) { backStackEntry ->
        val paradaId = requireNotNull(backStackEntry.arguments?.getString(BanRoutes.ParadaIdArg))
        val viewModel = rememberParadaFlowViewModel(
            navController = navController,
            paradaId = paradaId,
            repository = repository,
            clock = clock,
            zoneId = zoneId
        )
        val uiState by viewModel.uiState.collectAsState()

        HandleParadaFlowEffects(
            navController = navController,
            paradaId = paradaId,
            uiState = uiState,
            viewModel = viewModel,
            onReportIncident = onReportIncident,
            onCancelStop = onCancelStop
        )
        BackHandler {
            navController.popBackStack(BanRoutes.Rutas, false)
        }

        ParadasDetailScreen(
            uiState = uiState.detailUiState,
            onBackClick = {
                navController.popBackStack(BanRoutes.Rutas, false)
            },
            onObservationChange = viewModel::onObservationChanged,
            onArrivedClick = viewModel::onArrivedAtDestinationClick,
            onReportIncidentClick = viewModel::onReportIncidentClick
        )
    }

    composable(route = BanRoutes.EsperandoDonante) { backStackEntry ->
        val paradaId = requireNotNull(backStackEntry.arguments?.getString(BanRoutes.ParadaIdArg))
        val viewModel = rememberParadaFlowViewModel(
            navController = navController,
            paradaId = paradaId,
            repository = repository,
            clock = clock,
            zoneId = zoneId
        )
        val uiState by viewModel.uiState.collectAsState()

        HandleParadaFlowEffects(
            navController = navController,
            paradaId = paradaId,
            uiState = uiState,
            viewModel = viewModel,
            onReportIncident = onReportIncident,
            onCancelStop = onCancelStop
        )
        BackHandler {
            navController.popBackStack(BanRoutes.Rutas, false)
        }

        WaitingDonorScreen(
            uiState = uiState.waitingUiState,
            onBackClick = {
                navController.popBackStack(BanRoutes.Rutas, false)
            },
            onObservationChange = viewModel::onObservationChanged,
            onRegisterDonationClick = viewModel::onRegisterDonationCompletedClick,
            onAddExtraTimeClick = viewModel::onAddExtraTimeClick,
            onCancelStopClick = viewModel::onCancelStopClick
        )
    }

    composable(route = BanRoutes.RegistrarDonacion) { backStackEntry ->
        val paradaId = requireNotNull(backStackEntry.arguments?.getString(BanRoutes.ParadaIdArg))
        val viewModel = rememberParadaFlowViewModel(
            navController = navController,
            paradaId = paradaId,
            repository = repository,
            clock = clock,
            zoneId = zoneId
        )
        val uiState by viewModel.uiState.collectAsState()

        HandleParadaFlowEffects(
            navController = navController,
            paradaId = paradaId,
            uiState = uiState,
            viewModel = viewModel,
            onReportIncident = onReportIncident,
            onCancelStop = onCancelStop
        )

        DonacionesFormScreen(
            uiState = uiState.donationUiState,
            onBackClick = { navController.popBackStack() },
            onCategorySelected = viewModel::onDonationCategorySelected,
            onWeightChange = viewModel::onDonationWeightChanged,
            onValueChange = viewModel::onDonationValueChanged,
            onAddItemClick = viewModel::onAddDonationItemClick,
            onSaveClick = viewModel::onSaveDonationClick
        )
    }

    composable(route = BanRoutes.ParadaCompletada) { backStackEntry ->
        val paradaId = requireNotNull(backStackEntry.arguments?.getString(BanRoutes.ParadaIdArg))
        val viewModel = rememberParadaFlowViewModel(
            navController = navController,
            paradaId = paradaId,
            repository = repository,
            clock = clock,
            zoneId = zoneId
        )
        val uiState by viewModel.uiState.collectAsState()

        HandleParadaFlowEffects(
            navController = navController,
            paradaId = paradaId,
            uiState = uiState,
            viewModel = viewModel,
            onReportIncident = onReportIncident,
            onCancelStop = onCancelStop
        )
        BackHandler {
            navController.popBackStack(BanRoutes.Rutas, false)
        }

        ParadasCompletedScreen(
            uiState = uiState.completedUiState,
            onBackClick = {
                navController.popBackStack(BanRoutes.Rutas, false)
            }
        )
    }
}

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
private fun rememberParadaFlowViewModel(
    navController: NavHostController,
    paradaId: String,
    repository: ParadasRepository,
    clock: Clock,
    zoneId: ZoneId
): ParadaFlowViewModel {
    val owner = remember(paradaId) {
        navController.getBackStackEntry(BanRoutes.paradaFlow(paradaId))
    }
    val factory = remember(paradaId, repository, clock, zoneId) {
        ParadaFlowViewModelFactory(
            paradaId = paradaId,
            repository = repository,
            clock = clock,
            zoneId = zoneId
        )
    }

    return viewModel(
        viewModelStoreOwner = owner,
        key = "parada_flow_$paradaId",
        factory = factory
    )
}

@Composable
private fun HandleParadaFlowEffects(
    navController: NavController,
    paradaId: String,
    uiState: ParadaFlowUiState,
    viewModel: ParadaFlowViewModel,
    onReportIncident: (String) -> Unit,
    onCancelStop: (String) -> Unit
) {
    HandleParadaFlowCallbacks(
        uiState = uiState,
        viewModel = viewModel,
        onReportIncident = onReportIncident,
        onCancelStop = onCancelStop
    )

    LaunchedEffect(uiState.pendingNavigation) {
        // La navegación concreta vive aquí y no en el ViewModel para
        // mantener separadas la intención de negocio y la librería de UI.
        when (uiState.pendingNavigation) {
            ParadaFlowDestination.Detail -> {
                navController.navigate(BanRoutes.detalleParada(paradaId))
                viewModel.consumeNavigation()
            }

            ParadaFlowDestination.Waiting -> {
                // Una vez confirmada la llegada, la pantalla de detalle deja
                // de ser válida para esta parada y se reemplaza por espera.
                navController.navigate(BanRoutes.esperandoDonante(paradaId)) {
                    popUpTo(BanRoutes.detalleParada(paradaId)) {
                        inclusive = true
                    }
                }
                viewModel.consumeNavigation()
            }

            ParadaFlowDestination.RegisterDonation -> {
                navController.navigate(BanRoutes.registrarDonacion(paradaId))
                viewModel.consumeNavigation()
            }

            ParadaFlowDestination.Completed -> {
                // Al completarse la donación, la pantalla de espera ya no debe
                // permanecer en el back stack de esta parada.
                navController.navigate(BanRoutes.paradaCompletada(paradaId)) {
                    popUpTo(BanRoutes.esperandoDonante(paradaId)) {
                        inclusive = true
                    }
                }
                viewModel.consumeNavigation()
            }

            null -> Unit
        }
    }
}

@Composable
private fun HandleParadaFlowCallbacks(
    uiState: ParadaFlowUiState,
    viewModel: ParadaFlowViewModel,
    onReportIncident: (String) -> Unit,
    onCancelStop: (String) -> Unit
) {
    LaunchedEffect(uiState.pendingExternalAction) {
        when (val action = uiState.pendingExternalAction) {
            is ParadaFlowExternalAction.ReportIncident -> {
                onReportIncident(action.paradaId)
                viewModel.consumeExternalAction()
            }

            is ParadaFlowExternalAction.CancelStop -> {
                onCancelStop(action.paradaId)
                viewModel.consumeExternalAction()
            }

            null -> Unit
        }
    }
}
