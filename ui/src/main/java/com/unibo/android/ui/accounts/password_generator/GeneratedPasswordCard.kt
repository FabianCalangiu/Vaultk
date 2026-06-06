package com.unibo.android.ui.accounts.password_generator

import android.content.ClipData
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import kotlin.math.min


@Composable
fun GeneratedPasswordCard(
    password: String,
    entropyBits: Double,
    onGenerateClick: () -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                SelectionContainer(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = password,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                IconButton(
                    onClick = {
                        copyToClipboard(
                            context = context,
                            password = password
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy to clipboard"
                    )
                }
            }

            val strengthLabel = getStrengthLabel(entropyBits)

            Text(
                text = "Entropy %.1f bits".format(entropyBits),
                color = MaterialTheme.colorScheme.onSurface
            )

            LinearProgressIndicator(
                progress = {
                    min((entropyBits / 120).toFloat(), 1f)
                },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Strength: $strengthLabel",
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    onGenerateClick()
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Generate")
            }
        }
    }
}

/**
 * Function to call Android context and to copy the password to the user's clipboard
 */
private fun copyToClipboard(context: Context, password: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
    val clip = ClipData.newPlainText("Password", password)
    clipboard.setPrimaryClip(clip)
}

private fun getStrengthLabel(entropyBits: Double): String {
    return when {
        entropyBits < 40 -> "Very Weak"
        entropyBits < 60 -> "Weak"
        entropyBits < 80 -> "Good"
        entropyBits < 100 -> "Strong"
        else -> "Very Strong"
    }
}