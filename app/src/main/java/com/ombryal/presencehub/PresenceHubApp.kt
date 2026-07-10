package com.ombryal.presencehub

import androidx.compose.runtime.Composable
import com.ombryal.presencehub.navigation.AppNavigation
import com.ombryal.presencehub.plugins.PluginRegistryEntry

@Composable
fun PresenceHubApp(
    availablePlugins: List<PluginRegistryEntry>,
    onRefreshPlugins: () -> Unit,
    onInstallPlugin: (PluginRegistryEntry) -> Unit,
    onStartRpc: () -> Unit,
    onStopRpc: () -> Unit
) {
    AppNavigation(
        availablePlugins = availablePlugins,
        onRefreshPlugins = onRefreshPlugins,
        onInstallPlugin = onInstallPlugin,
        onStartRpc = onStartRpc,
        onStopRpc = onStopRpc
    )
}
