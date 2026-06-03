package com.unibo.android.ui.accounts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unibo.android.domain.models.AccountEntryModel

@Composable
fun AccountEntryCard(
    entry: AccountEntryModel,
    onClose: () -> Unit
) {
    Card {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = entry.title,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text("Username / E-mail")

            Text(
                text = entry.email,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text("Password")

            Text(
                text = entry.password,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}