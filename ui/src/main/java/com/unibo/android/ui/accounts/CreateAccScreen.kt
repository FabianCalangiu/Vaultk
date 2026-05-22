package com.unibo.android.ui.accounts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun CreateAccScreen() {
    var accountTitle by remember {
        mutableStateOf("")
    }

    var emailAccounts by remember {
        mutableStateOf("")
    }

    var passwordAccount by remember {
        mutableStateOf("")
    }
}