package com.example.recolectores.feature.autenticacion.domain.usecase

import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test

class ValidarCredencialesLoginUseCaseTest {

    private val useCase = ValidarCredencialesLoginUseCase()

    @Test
    fun invoke_retorna_error_cuando_el_correo_esta_vacio() {
        val result = useCase(
            correo = "",
            contrasena = "Segura123"
        )

        assertEquals("Ingrese su correo", result.correoError)
        assertNull(result.contrasenaError)
        assertFalse(result.esValido)
    }

    @Test
    fun invoke_retorna_error_cuando_el_correo_no_tiene_formato_valido() {
        val result = useCase(
            correo = "correo-invalido",
            contrasena = "Segura123"
        )

        assertEquals("Ingrese un correo valido", result.correoError)
        assertNull(result.contrasenaError)
        assertFalse(result.esValido)
    }

    @Test
    fun invoke_retorna_error_cuando_la_contrasena_esta_vacia() {
        val result = useCase(
            correo = "recolector@ban.org",
            contrasena = ""
        )

        assertNull(result.correoError)
        assertEquals("Ingrese su contrasena", result.contrasenaError)
        assertFalse(result.esValido)
    }

    @Test
    fun invoke_retorna_exito_cuando_las_credenciales_son_validas() {
        val result = useCase(
            correo = "recolector@ban.org",
            contrasena = "Segura123"
        )

        assertNull(result.correoError)
        assertNull(result.contrasenaError)
        assertTrue(result.esValido)
    }
}
