package com.ombryal.presencehub.plugins

class PluginStore(
    private val repository: PluginRepository = PluginRepository()
) {
    private var remotePlugins: List<PluginRegistryEntry> = emptyList()

    fun refresh(): Boolean {
        val index = repository.fetchPluginIndex()
        remotePlugins = index?.plugins ?: emptyList()
        return index != null
    }

    fun getAvailablePlugins(): List<PluginRegistryEntry> {
        return remotePlugins
    }

    fun findById(pluginId: String): PluginRegistryEntry? {
        return remotePlugins.firstOrNull { it.pluginId == pluginId }
    }
}
