package com.unibo.android.ui.accounts

import android.graphics.Outline
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.unibo.android.ui.common.Header
import com.unibo.android.ui.theme.Header
import com.unibo.android.uicompose.navigation.Routes

@Composable
fun CreateAccScreen(
    navController: NavController,
    viewModel: CreateAccViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var showPassword by remember {
        mutableStateOf(false)
    }

    val generatedPassword = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.get<String>("generated_password")

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                CreateAccEvent.NavigateToVault -> {
                    navController.navigate(Routes.VAULT) {
                        popUpTo(Routes.INSERT_ACCOUNTS) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(generatedPassword) {
        generatedPassword?.let {
            viewModel.onPasswordAccountChange(it)

            navController.currentBackStackEntry
                ?.savedStateHandle
                ?.remove<String>("generated_password")
        }
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
                    value = uiState.accountTitle,
                    onValueChange = viewModel::onAccountTitleChange,
                    label = {
                        Text("Insert title")
                            },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiState.emailAccount,
                    onValueChange = viewModel::onEmailAccountChange,
                    label = {
                        Text("Insert e-mail/username")
                            },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiState.websiteAccount,
                    onValueChange = viewModel::onWebsiteAccountChange,
                    label = {
                        Text("Insert website url: Ex. google.com")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiState.passwordAccount,
                    onValueChange = viewModel::onPasswordAccountChange,
                    label = {
                        Text("Insert password")
                    },

                    visualTransformation =
                        if (showPassword)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),

                    trailingIcon = {

                        IconButton(
                            onClick = {
                                showPassword = !showPassword
                            }
                        ) {

                            Icon(
                                imageVector =
                                    if (showPassword)
                                        Icons.Default.VisibilityOff
                                    else
                                        Icons.Default.Visibility,
                                contentDescription =
                                    if (showPassword)
                                        "Hide password"
                                    else
                                        "Show password"
                            )
                        }
                    },

                    modifier = Modifier.fillMaxWidth()
                )

                uiState.errorMessage?.let{ error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            navController.navigate(Routes.PASSWORD_GENERATOR)
                                  },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Generate Password")
                    }

                    Button(
                        onClick = viewModel::onSubmit,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Create Account")
                    }
                }
            }
        }
    }
}