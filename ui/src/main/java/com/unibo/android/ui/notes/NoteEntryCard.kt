package com.unibo.android.ui.notes

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unibo.android.domain.models.NoteEntryModel
import com.unibo.android.ui.theme.Surface

@Composable
fun NoteEntryCard(
    entry: NoteEntryModel,
    onDelete: () -> Unit,
    onUpdate: (NoteEntryModel) -> Unit
) {
    val scrollState = rememberScrollState()

    var editMode by remember {
        mutableStateOf(false)
    }

    var title by remember {
        mutableStateOf(entry.title)
    }

    var content by remember {
        mutableStateOf(entry.content)
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = Surface)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .verticalScroll(scrollState)
        ) {

            if (editMode) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it }
                )

                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it }
                )
            } else {
                Text(
                    text = entry.title,
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(entry.content)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                if (editMode) {
                    Button(
                        onClick = {
                            title = entry.title
                            content = entry.content
                            editMode = false
                        }
                    ) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = {
                            onUpdate(entry.copy(title = title, content = content))
                            editMode = false
                        }
                    ) {
                        Text("Save")
                    }
                } else {
                    Button(
                        onClick = {
                            editMode = true
                        }
                    ) {
                        Text("Edit")
                    }

                    Button(onClick = onDelete) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}