package com.unibo.android.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.unibo.android.uicompose.login.LoginCard
import com.unibo.android.uicompose.navigation.Routes

@Composable
fun LoginScreen(
    navController: NavController
) {
    LoginCard(
        onSubmit = { username, password ->
            println("Entered credentials")

            // check if credentials are okay and then

            navController.navigate(Routes.VAULT)
        }
    )
}