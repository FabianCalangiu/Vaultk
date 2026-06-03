package com.unibo.android.ui.accounts

import android.content.ClipData
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unibo.android.domain.models.AccountEntryModel
import com.unibo.android.ui.theme.Surface


@Composable
fun AccountEntryCard(
    entry: AccountEntryModel,
    onClose: () -> Unit,
    onDelete: () -> Unit,
    onUpdate: (AccountEntryModel) -> Unit
) {
    val clipboardManager = LocalClipboardManager.current

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
            modifier = Modifier.padding(30.dp)
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
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                "Username / E-mail",
                fontSize = 19.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )

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
                modifier = Modifier.height(15.dp)
            )

            Text("Password", fontSize = 19.sp)

            if (editMode) {

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    }
                )

            } else {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = password,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    IconButton(
                        onClick = {
                            clipboardManager.setText(
                                AnnotatedString(password)
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copy"
                        )
                    }
                }
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