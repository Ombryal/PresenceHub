package com.ombryal.presencehub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ombryal.presencehub.plugins.PluginRegistryEntry
import com.ombryal.presencehub.ui.about.AboutScreen
import com.ombryal.presencehub.ui.account.AccountScreen
import com.ombryal.presencehub.ui.addapp.AddAppScreen
import com.ombryal.presencehub.ui.addapp.PluginDetailsScreen
import com.ombryal.presencehub.ui.home.HomeScreen
import com.ombryal.presencehub.ui.settings.SettingsScreen

object Routes {
    const val HOME = "home"
    const val ACCOUNT = "account"
    const val ABOUT = "about"
    const val SETTINGS = "settings"
    const val ADD_APP = "add_app"
    const val PLUGIN_DETAILS = "plugin_details"
}

@Composable
fun AppNavigation(
    availablePlugins: List<PluginRegistryEntry>,
    onRefreshPlugins: () -> Unit,
    onInstallPlugin: (PluginRegistryEntry) -> Unit,
    onStartRpc: () -> Unit,
    onStopRpc: () -> Unit
) {
    val navController = rememberNavController()
    var selectedPlugin: PluginRegistryEntry? = null

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onOpenAccount = { navController.navigate(Routes.ACCOUNT) },
                onOpenAbout = { navController.navigate(Routes.ABOUT) },
                onOpenSettings = { navController.navigate(Routes.SETTINGS) },
                onOpenAddApp = { navController.navigate(Routes.ADD_APP) }
            )
        }

        composable(Routes.ACCOUNT) {
            AccountScreen(
                onBack = { navController.popBackStack() },
                onStartRpc = onStartRpc,
                onStopRpc = onStopRpc
            )
        }

        composable(Routes.ABOUT) {
            AboutScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                onBack = { navController.popBackStack() },
                onStartRpc = onStartRpc,
                onStopRpc = onStopRpc,
                onRefreshPlugins = onRefreshPlugins
            )
        }

        composable(Routes.ADD_APP) {
            AddAppScreen(
                availablePlugins = availablePlugins,
                onRefresh = onRefreshPlugins,
                onInstall = onInstallPlugin,
                onOpenDetails = { plugin ->
                    selectedPlugin = plugin
                    navController.navigate(Routes.PLUGIN_DETAILS)
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PLUGIN_DETAILS) {
            val plugin = selectedPlugin
            if (plugin != null) {
                PluginDetailsScreen(
                    plugin = plugin,
                    onInstall = onInstallPlugin,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
