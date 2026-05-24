package com.unibo.android.ui.accounts.password_generator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.unibo.android.domain.usecases.GeneratePasswordUseCase

import com.unibo.android.ui.common.Header

@Composable
fun PasswordGeneratorScreen() {

    /**
     * Password generation use case.
     */
    val generatePasswordUseCase = remember {
        GeneratePasswordUseCase()
    }

    /**
     * Generated password state.
     */
    var generatedPassword by remember {
        mutableStateOf("Click generate")
    }

    /**
     * Password entropy state.
     */
    var entropyBits by remember {
        mutableDoubleStateOf(0.0)
    }

    /**
     * Password length state.
     */
    var passwordLength by remember {
        mutableFloatStateOf(16f)
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

    /**
     * Main screen container.
     */
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        /**
         * Full-width header.
         */
        Header("Password generator")

        /**
         * Screen content container.
         */
        Column(
            modifier = Modifier.padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            /**
             * Generated password display card.
             */
            GeneratedPasswordCard(

                password = generatedPassword,

                entropyBits = entropyBits,

                onGenerateClick = {

                    val result = generatePasswordUseCase(

                        length = passwordLength.toInt(),

                        includeNumbers = includeNumbers,

                        includeSpecialChars = includeSpecialChars,

                        includeUppercase = includeUppercase,

                        includeLowercase = includeLowercase
                    )

                    /**
                     * Update UI state.
                     */
                    generatedPassword = result.password

                    entropyBits = result.entropyBits
                }
            )

            /**
             * Password options section.
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
             * Confirm button.
             */
            ConfirmPasswordButton()
        }
    }
}