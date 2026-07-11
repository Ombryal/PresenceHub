package com.ombryal.presencehub.plugins

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class RemotePluginManifestRepository {

    fun fetchManifest(manifestUrl: String): PluginPackageManifest? {
        return try {
            val connection = openConnection(manifestUrl)
            val response = readResponse(connection)
            parseManifest(response)
        } catch (_: Throwable) {
            null
        }
    }

    private fun parseManifest(rawJson: String): PluginPackageManifest? {
        return try {
            val obj = org.json.JSONObject(rawJson)
            PluginPackageManifest(
                pluginId = obj.optString("pluginId"),
                name = obj.optString("name"),
                version = obj.optString("version"),
                apiVersion = obj.optInt("apiVersion", 1),
                author = obj.optString("author"),
                description = obj.optString("description").takeIf { it.isNotBlank() },
                packageUrl = obj.optString("packageUrl"),
                checksumSha256 = obj.optString("checksumSha256").takeIf { it.isNotBlank() },
                signatureUrl = obj.optString("signatureUrl").takeIf { it.isNotBlank() },
                verified = obj.optBoolean("verified", false)
            )
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
            useCaches = false
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
