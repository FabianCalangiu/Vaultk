package com.unibo.android.ui.accounts.password_generator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unibo.android.ui.common.Header

@Composable
fun PasswordGeneratorScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Header("Password generator")

        /**
         * Generated password card
         */
        GeneratedPasswordCard(password = "password", { println("Password generated") })

        /**
         * Password options selections
         */
        PasswordOptionsSection()

        /**
         * Confirm button
         */
        ConfirmPasswordButton()
    }
}