package com.unibo.android.uicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.unibo.android.vaultk.ui.navigation.AppNavigation
import com.unibo.android.uicompose.ui.theme.CorsoLp2526Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            CorsoLp2526Theme {
                AppNavigation()
            }
        }
    }
}