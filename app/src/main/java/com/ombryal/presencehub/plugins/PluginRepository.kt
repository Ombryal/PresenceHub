package com.ombryal.presencehub.plugins

import com.ombryal.presencehub.utils.Constants
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class PluginRepository {

    fun fetchPluginIndex(): PluginIndex? {
        return try {
            val connection = openConnection(Constants.PLUGIN_INDEX_URL)
            val response = readResponse(connection)

            if (response.isNotBlank()) {
                PluginIndex(
                    version = 1,
                    plugins = listOf(
                        PluginRegistryEntry(
                            pluginId = "youtube",
                            name = "YouTube",
                            version = "1.0.0",
                            apiVersion = 1,
                            downloadUrl = "${Constants.PLUGIN_REPO_BASE_URL}/plugins/youtube/youtube-plugin.apk",
                            checksumSha256 = "",
                            signature = "",
                            description = "Official YouTube Presence plugin.",
                            verified = true
                        )
                    )
                )
            } else {
                null
            }
        } catch (_: Throwable) {
            null
        }
    }

    private fun openConnection(rawUrl: String): HttpURLConnection {
        val url = URL(rawUrl)
        return (url.openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = 10_000
            readTimeout = 10_000
        }
    }

    private fun readResponse(connection: HttpURLConnection): String {
        return connection.inputStream.use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                reader.readText()
            }
        }
    }
}
