package com.ombryal.presencehub.plugins

data class RemotePluginPackage(
    val pluginId: String,
    val name: String,
    val version: String,
    val apiVersion: Int,
    val author: String,
    val description: String? = null,
    val manifestUrl: String,
    val packageUrl: String,
    val checksumUrl: String? = null,
    val signatureUrl: String? = null,
    val verified: Boolean = false
)
