package com.unibo.android.ui.vault

import NoteEntryCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.unibo.android.domain.models.AccountEntryModel
import com.unibo.android.ui.common.Header
import com.unibo.android.uicompose.navigation.Routes
import kotlin.collections.emptyList
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.unibo.android.domain.di.UseCasesProvider
import com.unibo.android.domain.models.NoteEntryModel
import com.unibo.android.ui.common.EntryCard
import androidx.compose.ui.window.Dialog

@Composable
fun VaultScreen(navController: NavController) {
    var accounts by remember {
        mutableStateOf<List<AccountEntryModel>>(emptyList())
    }

    var notes by remember {
        mutableStateOf<List<NoteEntryModel>>(emptyList())
    }

    var selectedNote by remember {
        mutableStateOf<NoteEntryModel?>(null)
    }

    LaunchedEffect(Unit) {
        accounts = UseCasesProvider.getAccountsUseCase()
        notes = UseCasesProvider.getNotesUseCase()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Header("Vault")

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SectionCard(
                title = "Accounts"
            ) {
                accounts.forEach { accountEntry ->
                    EntryCard(
                        title = accountEntry.title,
                        onClick = {
                            // Yet to define
                        }
                    )
                }


                AddEntryCard(
                    text = "New Entry",
                    {
                        navController.navigate(Routes.INSERT_ACCOUNTS)
                    }
                )
            }

            SectionCard(
                title = "Notes"
            ) {
                notes.forEach { note ->
                    EntryCard(
                        title = note.title,
                        onClick = {
                            selectedNote = note
                        }
                    )
                }

                AddEntryCard(
                    text = "New Secure Note",
                    {
                        navController.navigate(Routes.INSERT_NOTES)
                    }
                )
            }
        }
        if (selectedNote != null) {
            Dialog(
                onDismissRequest = {
                    selectedNote = null
                }
            ) {
                NoteEntryCard(
                    title = selectedNote!!.title,
                    content = selectedNote!!.content
                )
            }
        }
    }
}