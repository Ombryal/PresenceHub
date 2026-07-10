package com.ombryal.presencehub

import androidx.compose.runtime.Composable
import com.ombryal.presencehub.navigation.AppNavigation
import com.ombryal.presencehub.plugins.PluginRegistryEntry
import com.ombryal.presencehub.plugins.PluginStoreState

@Composable
fun PresenceHubApp(
    storeState: PluginStoreState,
    onRefreshPlugins: () -> Unit,
    onInstallPlugin: (PluginRegistryEntry) -> Unit,
    onStartRpc: () -> Unit,
    onStopRpc: () -> Unit
) {
    AppNavigation(
        storeState = storeState,
        onRefreshPlugins = onRefreshPlugins,
        onInstallPlugin = onInstallPlugin,
        onStartRpc = onStartRpc,
        onStopRpc = onStopRpc
    )
}
