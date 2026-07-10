package com.ombryal.presencehub.rpc

import com.ombryal.presencehub.data.models.Presence

class RPCManager {

    private var currentPresence: Presence? = null

    fun updatePresence(presence: Presence) {
        currentPresence = presence
    }

    fun clearPresence() {
        currentPresence = null
    }

    fun getCurrentPresence(): Presence? = currentPresence
}
