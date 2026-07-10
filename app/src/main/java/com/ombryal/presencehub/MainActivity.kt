package com.ombryal.presencehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.ombryal.presencehub.plugins.PluginInstallManager
import com.ombryal.presencehub.plugins.PluginStore
import com.ombryal.presencehub.service.ServiceController
import com.ombryal.presencehub.ui.theme.PresenceHubTheme

class MainActivity : ComponentActivity() {

    private val pluginStore = PluginStore()
    private val pluginInstallManager = PluginInstallManager()

    private var availablePluginsState by mutableStateOf(emptyList<com.ombryal.presencehub.plugins.PluginRegistryEntry>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pluginStore.refresh()
        availablePluginsState = pluginStore.getAvailablePlugins()

        setContent {
            PresenceHubTheme {
                PresenceHubApp(
                    availablePlugins = availablePluginsState,
                    onRefreshPlugins = {
                        pluginStore.refresh()
                        availablePluginsState = pluginStore.getAvailablePlugins()
                    },
                    onInstallPlugin = { plugin ->
                        pluginInstallManager.install(plugin)
                    },
                    onStartRpc = { ServiceController.startRpcService(this) },
                    onStopRpc = { ServiceController.stopRpcService(this) }
                )
            }
        }
    }
}
