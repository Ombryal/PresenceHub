package com.ombryal.presencehub

import androidx.compose.runtime.Composable
import com.ombryal.presencehub.navigation.AppNavigation

@Composable
fun PresenceHubApp(
    onStartRpc: () -> Unit,
    onStopRpc: () -> Unit
) {
    AppNavigation(
        onStartRpc = onStartRpc,
        onStopRpc = onStopRpc
    )
}
