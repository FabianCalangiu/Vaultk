package com.unibo.android.ui.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.unibo.android.ui.common.Header
import com.unibo.android.ui.theme.Header
import com.unibo.android.uicompose.navigation.Routes

@Composable
fun CreateNoteScreen(
    navController: NavController,
    viewModel: CreateNoteViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                CreateNoteEvent.NavigateToVault -> {
                    navController.navigate(Routes.VAULT)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Header("New Note")

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
                    value = uiState.noteTitle,
                    onValueChange = viewModel::onNoteTitleChange,
                    label = {
                        Text("Insert title")
                            },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiState.noteText,
                    onValueChange = viewModel::onNoteTextChange,
                    label = {
                        Text("Insert text")
                            },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
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

                Button(
                    onClick = viewModel::onSubmit,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Create note")
                }
            }
        }
    }
}