package com.unibo.android.ui.accounts

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.unibo.android.ui.vault.VaultHeader

@Composable
fun CreateAccScreen() {
    var accountTitle by remember {
        mutableStateOf("")
    }

    var emailAccount by remember {
        mutableStateOf("")
    }

    var passwordAccount by remember {
        mutableStateOf("")
    }

    Column(

    ) {
        VaultHeader("New Account")
    }

}