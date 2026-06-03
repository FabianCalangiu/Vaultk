package com.unibo.android.ui.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unibo.android.domain.models.NoteEntryModel

@Composable
fun NoteEntryCard(
    entry: NoteEntryModel,
    onDelete: () -> Unit
) {

    val scrollState = rememberScrollState()

    Card {
        Column(
            modifier = Modifier.padding(24.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = entry.title,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(entry.content)

            Button(
                onClick = onDelete
            ) {
                Text("Delete")
            }
        }
    }
}