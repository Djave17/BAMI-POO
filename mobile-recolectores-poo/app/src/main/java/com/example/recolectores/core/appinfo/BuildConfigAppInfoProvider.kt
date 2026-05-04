package com.example.recolectores.core.appinfo

import com.example.recolectores.BuildConfig

/**
 * Implementacion de [AppInfoProvider] respaldada por `BuildConfig`.
 *
 *
 */
class BuildConfigAppInfoProvider : AppInfoProvider {

    override fun getVersionName(): String {
        return BuildConfig.VERSION_NAME
    }
}
