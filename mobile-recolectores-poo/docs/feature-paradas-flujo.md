# Flujo de Paradas

## Resumen

Este feature implementa el flujo visual y operativo de una parada del recolector usando cuatro pantallas separadas:

- `detalle_parada/{paradaId}`
- `esperando_donante/{paradaId}`
- `registrar_donacion/{paradaId}`
- `parada_completada/{paradaId}`

Las cuatro rutas comparten el mismo `ParadaFlowViewModel`, por lo que observaciones, timer y borrador de donación se conservan entre pantallas sin duplicar estado.

## Arquitectura aplicada

### 1. Fuente de verdad compartida

`ParadaFlowViewModel` es la fuente de verdad del flujo completo. No hay un ViewModel distinto por pantalla porque eso fragmentaría:

- el borrador de observaciones
- el reloj de espera
- el resumen acumulado de donación
- la navegación derivada del estado real de la parada

La navegación vive en `ParadaFlowNavigation.kt`, pero el `ViewModel` solo emite intención (`ParadaFlowDestination` o `ParadaFlowExternalAction`). Esto evita acoplar reglas de negocio con Compose Navigation.

### 2. Capas de datos

El flujo ya quedó preparado para una futura API en tres niveles:

- `ParadasDetailDto`
  - representa el payload técnico del endpoint
  - mantiene timestamps crudos como `String?`
- `ParadasDetail`
  - representa el dominio tipado
  - usa `Instant` para cálculos seguros de tiempo
- `ParadasDetailUiState` / `WaitingDonorUiState` / `DonacionesFormUiState` / `ParadasCompletedUiState`
  - representan datos ya listos para Compose
  - contienen labels, badges y textos formateados

La conversión DTO -> dominio se concentra en `ParadasMapper`.

La conversión dominio -> UI se concentra en `ParadaFlowUiMapper`.

## Contrato listo para endpoint

Los campos mínimos ya modelados en dominio para recibir backend son:

- `paradaId`
- `paradaNumero`
- `status`
- `donor.name`
- `branch.name`
- `addressLine`
- `scheduledArrivalAt`
- `arrivedAt`
- `waitLimitMinutes`
- `extraWaitMinutesTotal`
- `observationDraft`
- `completedAt`
- `donationSummary`
- `canReportIncident`
- `canCancelStop`
- `canRegisterArrival`

Esto permite reemplazar la fuente dummy por Retrofit, Ktor o cualquier cliente sin rehacer las pantallas.

## Timer y validación de negocio

### Regla principal

El timer es `timestamp-driven`, no `countdown-driven`.

La verdad del estado no vive en un contador local de Compose. Vive en:

- `arrivedAt`
- `waitLimitMinutes`
- `extraWaitMinutesTotal`
- `now`

Con esos cuatro datos, `WaitingState` deriva:

- `elapsedMinutes`
- `elapsedSeconds`
- `remainingMinutes`
- `remainingSeconds`
- `totalAllowedMinutes`
- `progress`
- `isExpired`

### Por qué esta decisión es importante

Si el recolector:

- navega entre pantallas
- minimiza la app
- vuelve desde background
- reabre una parada en estado de espera

el temporizador sigue siendo correcto porque se recalcula desde timestamps. El tick local solo sirve para refrescar la UI cada segundo.

### Regla de tiempo extra

Cada toque en `Dar 15 Minutos Extra` incrementa `extraWaitMinutesTotal` en 15.

No reinicia el reloj, no reemplaza el límite y no crea un segundo contador.

La regla queda encapsulada en `ExtendWaitingTimeUseCase` y validada por `WaitingState.EXTRA_TIME_INCREMENT_MINUTES`.

## Lógica de botones y navegación

### Detalle de parada

`Ya Llegué al Destino`

- registra la llegada real del recolector
- cambia `status` a `WaitingDonation`
- navega a `esperando_donante/{paradaId}`

`Reportar incidencia`

- no navega en esta iteración
- emite `ParadaFlowExternalAction.ReportIncident`
- deja el punto de integración listo para conectar un flujo futuro

### Esperando donante

`Registrar Donación Completada`

- no modifica todavía el dominio
- mueve al usuario a `registrar_donacion/{paradaId}`
- conserva observaciones y timer porque comparte el mismo `ParadaFlowViewModel`

`Dar 15 Minutos Extra`

- suma 15 minutos a `extraWaitMinutesTotal`
- recalcula el timer desde timestamps
- no reinicia `arrivedAt`
- solo se habilita cuando la ventana actual ya venció
- después de conceder una extensión, vuelve a deshabilitarse hasta que ese nuevo margen también expire

`Cancelar Parada`

- no cambia de pantalla en esta iteración
- emite `ParadaFlowExternalAction.CancelStop`
- deja preparada la integración futura de negocio

### Registrar donación

`Guardar Donación`

- valida el formulario
- si hay errores, los deja asociados al item/campo correspondiente
- si es válido, genera `DonationSummary`
- cambia el `status` a `Completed`
- navega a `parada_completada/{paradaId}`

### Reglas de back stack

- `detalle -> esperando` hace `popUpTo(detalle)` con `inclusive = true`
  - evita volver a una pantalla pendiente cuando la parada ya inició espera
- `esperando -> completada` hace `popUpTo(esperando)` con `inclusive = true`
  - evita regresar a una espera ya cerrada
- el botón de back en detalle, espera y completada regresa a `rutas`
  - mantiene una salida consistente del subflujo operativo

## Donaciones

El formulario se modela con dos niveles:

- dominio editable
  - `Donaciones`
  - `DonacionesItem`
  - `DonacionesCategory`
- UI
  - `DonacionesFormUiState`
  - `DonationFormItemUiState`

Las cantidades se mantienen como `String` mientras el usuario escribe porque eso refleja el comportamiento real de un campo de texto. La validación y el parseo ocurren después en:

- `ValidateDonationFormUseCase`
- `SaveDonationUseCase`

`SaveDonationUseCase` no persiste todavía en backend. En esta iteración consolida un `DonationSummary` que luego se usa para completar la parada.

## Reemplazo futuro de dummies por API

Para conectar el backend no hace falta cambiar pantallas. Solo hay que sustituir:

1. `ParadasDummyData.createSeedStops(...)`
2. `ParadasRepositoryImpl`

por una implementación real que:

- consulte el endpoint de detalle
- rehidrate `ParadasDetailDto`
- use `ParadasMapper.toDomain(...)`
- exponga `observeStop(paradaId)`
- persista mutaciones operativas

La UI seguirá recibiendo el mismo dominio y los mismos `UiState`.

## Componentes reutilizados o globalizados

Se reutilizaron o crearon como piezas compartidas:

- `BanButton`
- `BanOutlinedButton`
- `BanTextField`
- `BanSurfaceCard`
- `ParadaFlowScreenScaffold`
- `ParadaTopBar`
- `ParadaStatusBadge`
- `ParadasInfoCard`
- `ParadaObservationCard`
- `WaitingTimerCard`

Esto reduce duplicación y mantiene consistencia visual entre detalle, espera, donación y completada.

## Verificación actual

Verificado con:

- `:app:testDebugUnitTest`
- `:app:assembleDebug`

No se ejecutaron `androidTest` ni validación visual automatizada sobre emulador en esta iteración.
