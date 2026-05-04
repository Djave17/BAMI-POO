package com.example.recolectores.core.appinfo

import com.example.recolectores.BuildConfig
import org.junit.Assert.assertEquals
import org.junit.Test

class BuildConfigAppInfoProviderTest {

    @Test
    fun getVersionName_retorna_la_version_configurada_en_build_config() {
        val provider = BuildConfigAppInfoProvider()

        assertEquals(BuildConfig.VERSION_NAME, provider.getVersionName())
    }
}
