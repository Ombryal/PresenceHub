package com.ombryal.presencehub.plugins

import org.json.JSONArray
import org.json.JSONObject

object RemotePluginIndexParser {

    fun parse(rawJson: String): RemotePluginIndex? {
        return try {
            val root = JSONObject(rawJson)
            val version = root.optInt("version", 1)
            val array = root.optJSONArray("plugins") ?: JSONArray()

            val plugins = buildList {
                for (i in 0 until array.length()) {
                    val item = array.optJSONObject(i) ?: continue
                    add(parsePlugin(item))
                }
            }

            RemotePluginIndex(
                version = version,
                plugins = plugins
            )
        } catch (_: Throwable) {
            null
        }
    }

    private fun parsePlugin(obj: JSONObject): RemotePluginPackage {
        return RemotePluginPackage(
            pluginId = obj.optString("pluginId"),
            name = obj.optString("name"),
            version = obj.optString("version"),
            apiVersion = obj.optInt("apiVersion", 1),
            author = obj.optString("author"),
            description = obj.optString("description").takeIf { it.isNotBlank() },
            manifestUrl = obj.optString("manifestUrl"),
            packageUrl = obj.optString("packageUrl"),
            checksumUrl = obj.optString("checksumUrl").takeIf { it.isNotBlank() },
            signatureUrl = obj.optString("signatureUrl").takeIf { it.isNotBlank() },
            verified = obj.optBoolean("verified", false)
        )
    }
}
