package com.ombryal.presencehub.providers.youtube

import androidx.compose.runtime.Composable
import com.ombryal.presencehub.data.models.Presence
import com.ombryal.presencehub.plugins.PresencePlugin

class YouTubeProvider : PresencePlugin {

    override val pluginId: String = "youtube"
    override val displayName: String = "YouTube"
    override val version: String = "1.0.0"

    private val detector = YouTubeDetector()
    private var currentPresence: Presence? = null
    private var running = false

    override fun isAvailable(): Boolean {
        return true
    }

    override fun start() {
        running = true
        refresh()
    }

    override fun stop() {
        running = false
        currentPresence = null
    }

    fun refresh() {
        if (!running) return

        val mediaData = detector.detect()
        currentPresence = mediaData?.let { YouTubePresenceMapper.map(it) }
    }

    override fun getCurrentPresence(): Presence? {
        return currentPresence
    }

    @Composable
    override fun SettingsScreen() {
        // YouTube-specific settings UI later.
    }
}
