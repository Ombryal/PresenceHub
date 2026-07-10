package com.ombryal.presencehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ombryal.presencehub.plugins.PluginStore
import com.ombryal.presencehub.service.ServiceController
import com.ombryal.presencehub.ui.theme.PresenceHubTheme

class MainActivity : ComponentActivity() {

    private val pluginStore = PluginStore()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pluginStore.refresh()

        setContent {
            PresenceHubTheme {
                PresenceHubApp(
                    availablePlugins = pluginStore.getAvailablePlugins(),
                    onRefreshPlugins = {
                        pluginStore.refresh()
                    },
                    onInstallPlugin = { plugin ->
                        // Install flow will get more real later.
                        // For now this is just a placeholder action.
                    },
                    onStartRpc = { ServiceController.startRpcService(this) },
                    onStopRpc = { ServiceController.stopRpcService(this) }
                )
            }
        }
    }
}
