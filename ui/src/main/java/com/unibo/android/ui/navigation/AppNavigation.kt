package com.unibo.android.vaultk.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unibo.android.ui.login.Form
import com.unibo.android.ui.login.LoginScreen
import com.unibo.android.uicompose.navigation.Routes


// import com.unibo.android.vaultk.ui.login.LoginScreen
import com.unibo.android.vaultk.ui.splash.SplashScreen
// import com.unibo.android.vaultk.ui.vault.VaultScreen

/**
 * Navigation controller for the app. All destinations are defined here.
 *  **Please note that 'login' has yet not be defined since this commit, therefore the app will open briefly and close.**
 */
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(Routes.SPLASH) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Routes.LOGIN)
                }
            )

        }

        composable(Routes.FORM) {
            Form()
        }

//        composable(Routes.LOGIN) {
//            LoginScreen(navController)
//        }
    }
}