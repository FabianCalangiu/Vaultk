package com.unibo.android.ui.vault

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.unibo.android.ui.common.Header
import com.unibo.android.uicompose.navigation.Routes

@Composable
fun VaultScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Header("Vault")

        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SectionCard(
                title = "Accounts"
            ) {
                // Display entries

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
                // Display entries

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