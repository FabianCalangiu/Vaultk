package com.unibo.android.ui.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.unibo.android.ui.common.Header
import com.unibo.android.ui.theme.Header

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Header("New Account")

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(15.dp)
                .background(Header)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                OutlinedTextField(
                    value = accountTitle,
                    onValueChange = {
                        accountTitle = it
                    },
                    label = {
                        Text("Insert title")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = emailAccount,
                    onValueChange = {
                        emailAccount = it
                    },
                    label = {
                        Text("Insert email/username")
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = passwordAccount,
                    onValueChange = {
                        passwordAccount = it
                    },
                    label = {
                        Text("Insert password")
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            //API TO GENERATE PASSWORD
                        },
                        modifier = Modifier
                            .align(Alignment.End)
                    ) {
                        Text("Generate Password")
                    }

                    Button(
                        onClick = {
                            //API TO GENERATE PASSWORD
                        },
                        modifier = Modifier
                            .align(Alignment.End)
                    ) {
                        Text("Create Account")
                    }
                }
            }
        }
    }
}