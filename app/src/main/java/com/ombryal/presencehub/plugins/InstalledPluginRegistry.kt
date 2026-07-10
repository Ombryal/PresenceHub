package com.ombryal.presencehub.plugins

import android.content.Context

class InstalledPluginRegistry(context: Context) {

    private val preferences = context.getSharedPreferences(
        "installed_plugins",
        Context.MODE_PRIVATE
    )

    fun getInstalledPluginIds(): Set<String> {
        return preferences.getStringSet(KEY_INSTALLED_IDS, emptySet()) ?: emptySet()
    }

    fun isInstalled(pluginId: String): Boolean {
        return getInstalledPluginIds().contains(pluginId)
    }

    fun markInstalled(pluginId: String) {
        val updated = getInstalledPluginIds().toMutableSet()
        updated.add(pluginId)
        preferences.edit().putStringSet(KEY_INSTALLED_IDS, updated).apply()
    }

    fun markUninstalled(pluginId: String) {
        val updated = getInstalledPluginIds().toMutableSet()
        updated.remove(pluginId)
        preferences.edit().putStringSet(KEY_INSTALLED_IDS, updated).apply()
    }

    fun clearAll() {
        preferences.edit().remove(KEY_INSTALLED_IDS).apply()
    }

    private companion object {
        const val KEY_INSTALLED_IDS = "key_installed_ids"
    }
}
