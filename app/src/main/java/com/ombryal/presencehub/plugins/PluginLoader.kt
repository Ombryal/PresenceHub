package com.ombryal.presencehub.plugins

class PluginLoader {

    fun install(entry: PluginRegistryEntry): Boolean {
        if (entry.downloadUrl.isBlank()) return false
        if (entry.pluginId.isBlank()) return false
        return true
    }

    fun uninstall(pluginId: String): Boolean {
        return pluginId.isNotBlank()
    }
}
