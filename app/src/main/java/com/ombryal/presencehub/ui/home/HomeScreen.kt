package com.ombryal.presencehub.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ombryal.presencehub.data.models.Presence

@Composable
fun HomeScreen(
    onOpenAccount: () -> Unit,
    onOpenAbout: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenAddApp: () -> Unit
) {
    val samplePresence = Presence(
        pluginId = "youtube",
        providerName = "YouTube",
        title = "Waiting for YouTube",
        subtitle = "Enable notification access to begin",
        state = "Idle",
        buttonLabel = "Open YouTube",
        buttonUrl = "https://youtube.com"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "PresenceHub",
            style = MaterialTheme.typography.headlineLarge
        )

        Card(
            colors = CardDefaults.cardColors()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(text = "Current Activity", style = MaterialTheme.typography.titleMedium)
                Text(text = samplePresence.providerName)
                Text(text = samplePresence.title)
                samplePresence.subtitle?.let { Text(text = it) }
                samplePresence.state?.let { Text(text = it) }
            }
        }

        Button(onClick = onOpenAddApp) { Text("YouTube Plugin") }
        Button(onClick = onOpenSettings) { Text("Settings") }
        Button(onClick = onOpenAccount) { Text("Account") }
        Button(onClick = onOpenAbout) { Text("About") }
    }
}
