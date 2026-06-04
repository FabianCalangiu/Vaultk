package com.unibo.android.ui.vault

import com.unibo.android.ui.notes.NoteEntryCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.unibo.android.ui.accounts.AccountEntryCard
import com.unibo.android.ui.common.EntryCard
import com.unibo.android.ui.common.Header
import com.unibo.android.uicompose.navigation.Routes

@Composable
fun VaultScreen(
    navController: NavController,
    viewModel: VaultViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Header("Vault")

        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SectionCard(title = "Accounts") {
                uiState.accounts.forEach { accountEntry ->
                    EntryCard(
                        title = accountEntry.title,
                        onClick = {
                            viewModel.onSelectAccount(accountEntry)
                        }
                    )
                }
                AddEntryCard(
                    text = "New Entry",
                    onClick = {
                        navController.navigate(Routes.INSERT_ACCOUNTS)
                    }
                )
            }

            SectionCard(title = "Notes") {
                uiState.notes.forEach { note ->
                    EntryCard(
                        title = note.title,
                        onClick = {
                            viewModel.onSelectNote(note)
                        }
                    )
                }
                AddEntryCard(
                    text = "New Secure Note",
                    onClick = {
                        navController.navigate(Routes.INSERT_NOTES)
                    }
                )
            }
        }

        uiState.selectedNote?.let { note ->
            Dialog(onDismissRequest = {
                viewModel.onSelectNote(null)
            }) {
                NoteEntryCard(
                    entry = note,
                    onDelete = {
                        viewModel.onDeleteNote(note)
                               },
                    onUpdate = {
                        entry -> viewModel.onUpdateNote(entry)
                    }
                )
            }
        }
    }

    uiState.selectedAccount?.let {
        account ->
        Dialog(onDismissRequest = {
            viewModel.onSelectAccount(null)
        }) {
            AccountEntryCard(
                entry = account,
                onClose = {
                    viewModel.onSelectAccount(null)
                          },
                onDelete = {
                    viewModel.onDeleteAccount(account)
                           },
                onUpdate = {
                    updatedEntry -> viewModel.onUpdateAccount(updatedEntry)
                }
            )
        }
    }
}