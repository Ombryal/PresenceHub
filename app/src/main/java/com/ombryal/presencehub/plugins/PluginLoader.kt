package com.ombryal.presencehub.plugins

class PluginLoader(
    private val detailsRepository: PluginDetailsRepository = PluginDetailsRepository(),
    private val installManager: PluginInstallManager = PluginInstallManager()
) {

    fun load(plugin: PluginRegistryEntry): Boolean {
        val details = detailsRepository.fetchDetails(plugin) ?: return false
        val compatible = PluginPackageValidator.isCompatible(details)
        if (!compatible) return false

        return installManager.install(plugin)
    }

    fun unload(pluginId: String): Boolean {
        return installManager.uninstall(pluginId)
    }
}
