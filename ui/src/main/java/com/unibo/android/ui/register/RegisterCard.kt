package com.unibo.android.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unibo.android.domain.di.UseCasesProvider
import com.unibo.android.uicompose.navigation.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun onSubmit(email: String, password: String, navController: NavController, scope: CoroutineScope) {
    println("Entered credentials")

    scope.launch {

        val registerUseCase =
            UseCasesProvider.registerUseCase

        val result = registerUseCase(

            email = email,

            password = password
        )

        if (result.isSuccess) {

            navController.navigate(
                Routes.VAULT
            ) {
                popUpTo(
                    Routes.FORM_REGISTER
                ) {
                    inclusive = true
                }
            }

        } else {

            println(
                result.exceptionOrNull()?.message
            )
        }
    }
}

@Composable
fun RegisterCard(
    navController: NavController
) {

    val coroutineScope = rememberCoroutineScope()

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "LOGIN",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Routes.FORM_LOGIN) {
                                popUpTo(Routes.FORM_REGISTER) {
                                    inclusive = true
                                }
                            }
                        }
                )

                Text(
                    text = "REGISTER",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = {
                    Text("Insert e-mail")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text("Insert password")
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                },
                label = {
                    Text("Confirm password")
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Button(
                onClick = {
                    if(email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()) {
                        if(password == confirmPassword) {
                            onSubmit(email, password, navController, coroutineScope)
                        }

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Submit")
            }
        }
    }
}