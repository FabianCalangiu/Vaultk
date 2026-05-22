package com.unibo.android.ui.accounts.password_generator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable

@Composable
fun PasswordOptionsSection(
    passwordLength: Float,
    onPasswordLengthChange: (Float) -> Unit,

    includeNumbers: Boolean,
    onIncludeNumbersChange: (Boolean) -> Unit,

    includeSpecialChars: Boolean,
    onIncludeSpecialCharsChange: (Boolean) -> Unit,

    includeUppercase: Boolean,
    onIncludeUppercaseChange: (Boolean) -> Unit,

    includeLowercase: Boolean,
    onIncludeLowercaseChange: (Boolean) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Length: ${passwordLength.toInt()}"
                )

                Slider(
                    value = passwordLength,

                    onValueChange = {
                        onPasswordLengthChange(it)
                    },

                    valueRange = 4f..255f
                )

                /**
                 * Number switch
                 */
                PasswordOptionRow(
                    text = "Include numbers",
                    checked = includeNumbers,
                    onCheckedChange = onIncludeNumbersChange
                )

                /**
                 * Symbols switch
                 */
                PasswordOptionRow(
                    text = "Include special characters",
                    checked = includeSpecialChars,
                    onCheckedChange = onIncludeSpecialCharsChange
                )

                /**
                 * Uppercase switch
                 */
                PasswordOptionRow(
                    text = "Include uppercase",
                    checked = includeUppercase,
                    onCheckedChange = onIncludeUppercaseChange
                )

                /**
                 * Lowercase switch
                 */
                PasswordOptionRow(
                    text = "Include lowercase",
                    checked = includeLowercase,
                    onCheckedChange = onIncludeLowercaseChange
                )
            }
    }
}