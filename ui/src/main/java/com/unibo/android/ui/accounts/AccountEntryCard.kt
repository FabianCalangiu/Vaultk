package com.unibo.android.ui.accounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unibo.android.domain.models.AccountEntryModel
import com.unibo.android.ui.theme.Surface

@Composable
fun AccountEntryCard(
    entry: AccountEntryModel,
    onClose: () -> Unit,
    onDelete: () -> Unit,
    onUpdate: (AccountEntryModel) -> Unit
) {

    val scrollState = rememberScrollState()

    var editMode by remember {
        mutableStateOf(false)
    }

    var title by remember {
        mutableStateOf(entry.title)
    }

    var email by remember {
        mutableStateOf(entry.email)
    }

    var password by remember {
        mutableStateOf(entry.password)
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = Surface)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
                .verticalScroll(scrollState)
        ) {
            if (editMode) {

                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                    }
                )

            } else {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text("Username / E-mail")

            if (editMode) {

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    }
                )

            } else {

                Text(
                    text = email,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text("Password")

            if (editMode) {

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    }
                )

            } else {

                Text(
                    text = password,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        editMode = !editMode
                    }
                ) {

                    Text(
                        if (editMode)
                            "Cancel"
                        else
                            "Edit"
                    )
                }

                Button(
                    onClick = {
                        onUpdate(
                            entry.copy(
                                title = title,
                                email = email,
                                password = password
                            )
                        )
                        editMode = false
                    }
                ) {
                    Text("Save")
                }

                Button(
                    onClick = onDelete
                ) {
                    Text("Delete")
                }
            }
        }
    }
}