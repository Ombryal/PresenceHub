package com.ombryal.presencehub.providers.youtube

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.app.Notification
import com.ombryal.prestencehub.utils.Logger

class YouTubeNotificationListenerService : NotificationListenerService() {

    companion object {
        private const val YOUTUBE_PACKAGE = "com.google.android.youtube"
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        Logger.d("YouTubeNotificationListenerService connected")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        if (sbn.packageName != YOUTUBE_PACKAGE) return

        val extras = sbn.notification.extras

        val title = extras.getCharSequence(Notification.EXTRA_TITLE)?.toString()?.trim().orEmpty()
        val text = extras.getCharSequence(Notification.EXTRA_TEXT)?.toString()?.trim()
        val subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT)?.toString()?.trim()

        if (title.isBlank()) return

        val mediaData = YouTubeMediaData(
            title = title,
            channel = subText ?: text,
            videoUrl = null,
            thumbnailUrl = null,
            durationMs = null,
            positionMs = null,
            isPlaying = true
        )

        YouTubePlaybackStore.update(mediaData)
        Logger.d("YouTube notification captured: $title")
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        if (sbn.packageName == YOUTUBE_PACKAGE) {
            YouTubePlaybackStore.clear()
            Logger.d("YouTube notification removed")
        }
    }
}
