package com.ombryal.presencehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ombryal.presencehub.service.ServiceController
import com.ombryal.presencehub.ui.theme.PresenceHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PresenceHubTheme {
                PresenceHubApp(
                    onStartRpc = { ServiceController.startRpcService(this) },
                    onStopRpc = { ServiceController.stopRpcService(this) }
                )
            }
        }
    }
}
