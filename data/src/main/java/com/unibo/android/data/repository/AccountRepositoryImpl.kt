package com.unibo.android.data.repository

import android.content.Context
import com.unibo.android.data.local.db.VaultDatabase
import com.unibo.android.domain.repositories.AccountRepository

class AccountRepositoryImpl(context: Context) : AccountRepository {
    private val accountEntryDao = VaultDatabase.getInstance(context).accountEntryDao()
}

