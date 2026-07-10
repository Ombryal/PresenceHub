package com.ombryal.presencehub.providers.youtube

import com.ombryal.presencehub.data.models.Presence

object YouTubePresenceMapper {

    fun map(mediaData: YouTubeMediaData): Presence {
        val stateText = if (mediaData.isPlaying) "Watching" else "Paused"

        return Presence(
            pluginId = "youtube",
            providerName = "YouTube",
            title = mediaData.title,
            subtitle = mediaData.channel,
            state = stateText,
            largeImageKey = "youtube_logo",
            smallImageKey = if (mediaData.isPlaying) "play" else "pause",
            startTimestamp = mediaData.positionMs?.let { System.currentTimeMillis() - it },
            endTimestamp = mediaData.durationMs?.let {
                val start = mediaData.positionMs ?: 0L
                System.currentTimeMillis() + (it - start)
            },
            buttonLabel = "Watch on YouTube",
            buttonUrl = mediaData.videoUrl
        )
    }
}
