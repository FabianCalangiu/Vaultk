package com.unibo.android.vaultk.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unibo.android.ui.accounts.CreateAccScreen
import com.unibo.android.ui.accounts.password_generator.PasswordGeneratorScreen
import com.unibo.android.ui.login.FormLogin
import com.unibo.android.ui.notes.CreateNoteScreen
import com.unibo.android.ui.register.FormRegister
import com.unibo.android.ui.vault.VaultScreen
import com.unibo.android.uicompose.navigation.Routes


// import com.unibo.android.vaultk.ui.login.LoginScreen
import com.unibo.android.vaultk.ui.splash.SplashScreen
// import com.unibo.android.vaultk.ui.vault.VaultScreen

/**
 * Navigation controller for the app. All destinations are defined here.
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
                    navController.navigate(Routes.FORM_LOGIN) {
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
            VaultScreen(navController)
        }

        composable(Routes.INSERT_NOTES) {
            CreateNoteScreen()
        }

        composable(Routes.INSERT_ACCOUNTS) {
            CreateAccScreen(navController)
        }

        composable(Routes.PASSWORD_GENERATOR) {
            PasswordGeneratorScreen()
        }
    }
}