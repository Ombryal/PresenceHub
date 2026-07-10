package com.ombryal.presencehub.plugins

class PluginInstallManager(
    private val downloadManager: PluginDownloadManager = PluginDownloadManager(),
    private val trustManager: PluginTrustManager = PluginTrustManager()
) {

    fun install(entry: PluginRegistryEntry): Boolean {
        if (!trustManager.canInstall(entry)) return false

        val bytes = downloadManager.downloadBytes(entry.downloadUrl) ?: return false

        // Public key will be wired in later from the app security layer.
        val publicKeyPem = ""

        return trustManager.verifyDownloadedPlugin(
            pluginBytes = bytes,
            entry = entry,
            publicKeyPem = publicKeyPem
        )
    }

    fun uninstall(pluginId: String): Boolean {
        return pluginId.isNotBlank()
    }
}
