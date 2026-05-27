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

    suspend fun updateAccountEntry(accountEntry: AccountEntryEntity) {
        accountEntryDao.updateEntry(accountEntry)
    }

    suspend fun getAccountEntryById(id: Long) {
        accountEntryDao.getEntryById(id)
    }

    suspend fun getAllAccounts(userId: Long) {
        accountEntryDao.getAllEntries(userId)
    }
}