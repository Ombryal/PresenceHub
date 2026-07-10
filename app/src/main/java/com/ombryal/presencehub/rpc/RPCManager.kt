package com.ombryal.presencehub.rpc

import com.ombryal.presencehub.data.models.Presence
import com.ombryal.presencehub.plugins.PluginManager

class RPCManager(
    private val pluginManager: PluginManager
) {
    private var currentPresence: Presence? = null

    fun refreshPresence() {
        val newPresence = pluginManager.getCurrentPresence()

        if (newPresence != currentPresence) {
            currentPresence = newPresence
            if (newPresence != null) {
                sendToDiscord(newPresence)
            } else {
                clearDiscordPresence()
            }
        }
    }

    fun clearPresence() {
        currentPresence = null
        clearDiscordPresence()
    }

    private fun sendToDiscord(presence: Presence) {
        // Real Discord RPC implementation comes later.
    }

    private fun clearDiscordPresence() {
        // Clear Discord activity here later.
    }
}
