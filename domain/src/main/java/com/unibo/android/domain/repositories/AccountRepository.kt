package com.unibo.android.domain.repositories

import com.unibo.android.domain.models.AccountEntryModel

interface AccountRepository {
    suspend fun createEntry(entry: AccountEntryModel)
    suspend fun getAllEntries(userId: Long): List<AccountEntryModel>
    suspend fun deleteEntry(entry: AccountEntryModel)

    suspend fun updateEntry(entry: AccountEntryModel)
}