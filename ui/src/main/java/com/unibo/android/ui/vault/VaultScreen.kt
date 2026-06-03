package com.unibo.android.ui.vault

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

@Composable
fun VaultScreen(navController: NavController) {
    var accounts by remember {
        mutableStateOf<List<AccountEntryModel>>(emptyList())
    }

    var notes by remember {
        mutableStateOf<List<NoteEntryModel>>(emptyList())
    }

    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        accounts = UseCasesProvider.getAccountsUseCase()
        notes = UseCasesProvider.getNotesUseCase()
    }

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
                notes.forEach { notes ->
                    EntryCard(
                        title = notes.title,
                        onClick = {

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
    }
}