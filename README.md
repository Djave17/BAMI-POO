# BAMI / BAN - Aplicación móvil de recolectores

## Descripción del proyecto

BAMI / BAN es una aplicación móvil Android desarrollada para apoyar el proceso de recolección de donaciones en campo. La aplicación está orientada al recolector y permite visualizar la ruta asignada, consultar las paradas del día, gestionar el estado operativo de cada parada y registrar la información principal de las donaciones recolectadas.

El objetivo principal del proyecto es digitalizar parte del flujo operativo de recolección, sustituyendo procesos manuales o dispersos por una interfaz móvil organizada, clara y fácil de usar. La aplicación busca mejorar la trazabilidad de las paradas, el registro de donaciones y la experiencia del recolector durante su jornada.

Actualmente, el proyecto funciona como una base móvil estructurada con pantallas, navegación, componentes reutilizables y datos de demostración. La arquitectura está preparada para crecer hacia una integración real con API, persistencia local, sincronización y eventos operativos en tiempo real.

## Tecnologías utilizadas

- **Kotlin**: lenguaje principal de desarrollo de la aplicación Android.
- **Android nativo**: plataforma base del proyecto.
- **Jetpack Compose**: framework declarativo para construir la interfaz de usuario.
- **Material 3**: sistema de componentes visuales utilizado para botones, tarjetas, campos, temas y estilos.
- **Navigation Compose**: manejo de navegación entre pantallas y flujos internos.
- **ViewModel**: administración del estado de las pantallas y separación de lógica de presentación.
- **StateFlow**: observación reactiva de estados dentro de la aplicación.
- **Kotlin Coroutines**: manejo de operaciones asíncronas.
- **Gradle Kotlin DSL**: configuración del proyecto y dependencias.
- **Material Icons Extended**: uso de íconos extendidos de Material Design.
- **kotlinx-serialization-json**: soporte para serialización de datos en formato JSON.
- **JUnit / Kotlin Coroutines Test**: pruebas unitarias para validar lógica del proyecto.

### Tecnologías preparadas para futuras integraciones

Las siguientes tecnologías están contempladas dentro de la arquitectura, pero deben conectarse completamente en fases posteriores:

- **Room**: persistencia local de rutas, paradas y donaciones.
- **DataStore**: almacenamiento de token, sesión y preferencias.
- **Retrofit / OkHttp**: consumo de API REST.
- **WebSocket**: recepción de cambios operativos en tiempo real desde el módulo de Operaciones.
- **Hilt o AppContainer manual**: gestión de dependencias cuando se conecten servicios reales.

## Estructura general del proyecto

El proyecto está organizado como un único módulo Gradle llamado `:app`, pero internamente separa responsabilidades por paquetes funcionales:

```text
app/
└── src/main/java/com/example/recolectores/
    ├── core/
    ├── di/
    └── feature/
        ├── autenticacion/
        ├── inicio/
        ├── rutas/
        ├── paradas/
        ├── donaciones/
        ├── perfil/
        └── sincronizacion/
