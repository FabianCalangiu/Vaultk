package com.unibo.android.data.repository

import com.unibo.android.data.local.dao.AccountEntryDao
import com.unibo.android.data.local.entity.AccountEntryEntity

class AccountRepository(
    private val accountEntryDao: AccountEntryDao
) {

    suspend fun createAccountEntry(accountEntry: AccountEntryEntity) {
        accountEntryDao.insertEntry(accountEntry)
    }

    suspend fun deleteAccountEntry(accountEntry: AccountEntryEntity) {
        accountEntryDao.deleteEntry(accountEntry)
    }

    suspend fun getAllAccounts(userId: Long) {
        accountEntryDao.getAllEntries(userId)
    }
}