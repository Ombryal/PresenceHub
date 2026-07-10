package com.ombryal.presencehub.plugins

class PluginLoader {

    fun install(entry: PluginRegistryEntry): Boolean {
        // Later: download, verify checksum, verify signature, then load
        return entry.downloadUrl.isNotBlank()
    }

    fun uninstall(pluginId: String): Boolean {
        // Later: remove local plugin package
        return pluginId.isNotBlank()
    }
}
