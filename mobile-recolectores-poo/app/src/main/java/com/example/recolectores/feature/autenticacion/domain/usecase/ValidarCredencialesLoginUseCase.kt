package com.example.recolectores.feature.autenticacion.domain.usecase

/**
 * Resultado de validación de credenciales
 *
 * Propósito: Transportar los errores de validación del formulario de login en un formato simple para la capa de presentación.
 *
 * Capa: domain.
 *
 * Responsabilidades:
 * 
 *   - Representar si el correo y la contraseña cumplen las reglas mínimas del caso de uso.
 *   - Servir como salida estable entre la validación de dominio y el `UiState`.
 *   - No acceder a API, Room, DataStore o WebSocket.
 * 
 */
data class ResultadoValidacionCredencialesLogin(
    val correoError: String? = null,
    val contrasenaError: String? = null
) {
    val esValido: Boolean
        get() = correoError == null && contrasenaError == null
}

/**
 * Validación de credenciales de acceso
 *
 * Propósito: Aplicar reglas básicas del formulario antes de intentar un inicio de sesión real.
 *
 * Capa: domain.
 *
 * Responsabilidades:
 * 
 *   - Validar correo y contraseña de forma aislada de la UI.
 *   - Entregar errores listos para que el ViewModel actualice el `UiState`.
 *   - No llamar API, no leer DataStore y no manejar navegación.
 * 
 *
 * Nota: Este caso de uso solo valida entrada. El acceso real al backend debe vivir en otro caso de uso apoyado por repositorios.
 */
class ValidarCredencialesLoginUseCase {

    // La validacion vive fuera de Compose para poder reutilizarse y probarse en aislamiento.
    operator fun invoke(
        correo: String,
        contrasena: String
    ): ResultadoValidacionCredencialesLogin {
        val correoError = when {
            correo.isBlank() -> "Ingrese su correo"
            !correo.matches(CORREO_REGEX) -> "Ingrese un correo valido"
            else -> null
        }

        val contrasenaError = if (contrasena.isBlank()) {
            "Ingrese su contrasena"
        } else {
            null
        }

        return ResultadoValidacionCredencialesLogin(
            correoError = correoError,
            contrasenaError = contrasenaError
        )
    }

    private companion object {
        val CORREO_REGEX = Regex("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    }
}
