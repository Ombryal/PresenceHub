package com.ombryal.presencehub.providers.youtube

data class YouTubeMediaData(
    val title: String,
    val channel: String? = null,
    val videoUrl: String? = null,
    val thumbnailUrl: String? = null,
    val durationMs: Long? = null,
    val positionMs: Long? = null,
    val isPlaying: Boolean = false
)

class YouTubeDetector {

    fun detect(): YouTubeMediaData? {
        // Real detection logic goes here later
        // This is only a PH so the app structure is ready.
        return null
    }
}
