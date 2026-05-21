package com.unibo.android.vaultk.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unibo.android.ui.login.FormLogin
import com.unibo.android.ui.register.FormRegister
import com.unibo.android.ui.vault.VaultScreen
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
                    navController.navigate(Routes.FORM_LOGIN)
                    navController.navigate(Routes.FORM) {
                        popUpTo(Routes.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            )

        }

        composable(Routes.FORM_LOGIN) {
            FormLogin(navController)
        }

        composable(Routes.FORM_REGISTER) {
            FormRegister(navController)
        }

        composable(Routes.VAULT) {
            VaultScreen()
        }
    }
}