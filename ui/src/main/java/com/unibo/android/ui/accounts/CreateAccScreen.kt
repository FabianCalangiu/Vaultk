package com.unibo.android.ui.accounts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.unibo.android.ui.common.Header

@Composable
fun CreateAccScreen() {
    var accountTitle by remember {
        mutableStateOf("")
    }

    var emailAccount by remember {
        mutableStateOf("")
    }

    var passwordAccount by remember {
        mutableStateOf("")
    }

    Column() {
        Header("New Account")

        Box() {
            OutlinedTextField(
                value = accountTitle,
                onValueChange = {
                    accountTitle = it
                },
                label = {
                    Text("Title")
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = emailAccount,
                onValueChange = {
                    emailAccount = it
                },
                label = {
                    Text("Email/Username")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = passwordAccount,
                onValueChange = {
                    passwordAccount = it
                },
                label = {
                    Text("Password")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Button(
                onClick = {
                    // Save note
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Generate Password")
            }
        }
    }

}