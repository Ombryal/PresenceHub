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
        // TODO: Connect this to the real Discord RPC implementation later.
        // This is only the app-side bridge for now.
    }

    private fun clearDiscordPresence() {
        // TODO: Clear Discord activity here.
    }
}
