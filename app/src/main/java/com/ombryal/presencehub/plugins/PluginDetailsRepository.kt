package com.ombryal.presencehub.plugins

class PluginDetailsRepository(
    private val manifestRepository: RemotePluginManifestRepository = RemotePluginManifestRepository()
) {

    fun fetchDetails(plugin: PluginRegistryEntry): PluginPackageManifest? {
        if (plugin.downloadUrl.isBlank()) return null

        val manifestUrl = plugin.downloadUrl
            .replace("plugin.apk", "manifest.json")

        return manifestRepository.fetchManifest(manifestUrl)
    }
}
