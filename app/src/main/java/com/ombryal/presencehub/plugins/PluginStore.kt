package com.ombryal.presencehub.plugins

class PluginStore {

    private val remotePlugins = mutableListOf<PluginRegistryEntry>()

    fun loadMockData() {
        remotePlugins.clear()
        remotePlugins.add(
            PluginRegistryEntry(
                pluginId = "youtube",
                name = "YouTube Plugin",
                version = "1.0.0",
                apiVersion = 1,
                downloadUrl = "https://github.com/Ombryal/PresenceHub/releases",
                description = "Detects YouTube playback and sends presence to Discord.",
                verified = true
            )
        )
    }

    fun getAvailablePlugins(): List<PluginRegistryEntry> {
        return remotePlugins.toList()
    }

    fun findById(pluginId: String): PluginRegistryEntry? {
        return remotePlugins.firstOrNull { it.pluginId == pluginId }
    }
}
