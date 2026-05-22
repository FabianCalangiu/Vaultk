package com.unibo.android.ui.accounts.password_generator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.unibo.android.ui.common.Header

@Composable
fun PasswordGeneratorScreen() {

    /**
     * Generated password state.
     */
    var generatedPassword by remember {
        mutableStateOf("Click generate")
    }

    /**
     * Password length state.
     */
    var passwordLength by remember {
        mutableStateOf(16f)
    }

    /**
     * Password options state.
     */
    var includeNumbers by remember {
        mutableStateOf(true)
    }

    var includeSpecialChars by remember {
        mutableStateOf(true)
    }

    var includeUppercase by remember {
        mutableStateOf(true)
    }

    var includeLowercase by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        /**
         * Header
         */
        Header("Password generator")

        /**
         * Generated password card
         */
        GeneratedPasswordCard(

            password = generatedPassword,

            onGenerateClick = {

                generatedPassword = generatePassword(
                    length = passwordLength.toInt(),
                    includeNumbers = includeNumbers,
                    includeSpecialChars = includeSpecialChars
                )
            }
        )

        /**
         * Password options
         */
        PasswordOptionsSection(

            passwordLength = passwordLength,

            onPasswordLengthChange = {
                passwordLength = it
            },

            includeNumbers = includeNumbers,

            onIncludeNumbersChange = {
                includeNumbers = it
            },

            includeSpecialChars = includeSpecialChars,

            onIncludeSpecialCharsChange = {
                includeSpecialChars = it
            },

            includeUppercase = includeUppercase,

            onIncludeUppercaseChange = {
                includeUppercase = it
            },

            includeLowercase = includeLowercase,

            onIncludeLowercaseChange = {
                includeLowercase = it
            }
        )

        /**
         * Confirm button
         */
        ConfirmPasswordButton()
    }
}