package com.unibo.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.unibo.android.vaultk.ui.navigation.AppNavigation
import com.unibo.android.ui.theme.VaultkTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            VaultkTheme {
                AppNavigation()
            }
        }
    }
}