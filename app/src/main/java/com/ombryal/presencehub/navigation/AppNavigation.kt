package com.ombryal.presencehub.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ombryal.presencehub.plugins.PluginRegistryEntry
import com.ombryal.presencehub.ui.about.AboutScreen
import com.ombryal.presencehub.ui.account.AccountScreen
import com.ombryal.presencehub.ui.addapp.AddAppScreen
import com.ombryal.presencehub.ui.home.HomeScreen
import com.ombryal.presencehub.ui.settings.SettingsScreen

object Routes {
    const val HOME = "home"
    const val ACCOUNT = "account"
    const val ABOUT = "about"
    const val SETTINGS = "settings"
    const val ADD_APP = "add_app"
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
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.ADD_APP) {
            AddAppScreen(
                availablePlugins = availablePlugins,
                onRefresh = onRefreshPlugins,
                onInstall = onInstallPlugin,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
