package com.ombryal.presencehub.plugins

data class PluginDescriptor(
    val pluginId: String,
    val name: String,
    val version: String,
    val apiVersion: Int,
    val author: String,
    val description: String? = null,
    val verified: Boolean = false,
    val installed: Boolean = false,
    val installedVersion: String? = null,
    val updateAvailable: Boolean = false
)
