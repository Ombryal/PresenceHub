package com.ombryal.presencehub.plugins

class PluginStore(
    private val repository: PluginRepository = PluginRepository()
) {
    private var remotePlugins: List<PluginRegistryEntry> = emptyList()

    fun refresh() {
        val index = repository.fetchPluginIndex()
        remotePlugins = index?.plugins ?: emptyList()
    }

    fun getAvailablePlugins(): List<PluginRegistryEntry> {
        return remotePlugins
    }

    fun findById(pluginId: String): PluginRegistryEntry? {
        return remotePlugins.firstOrNull { it.pluginId == pluginId }
    }
}
