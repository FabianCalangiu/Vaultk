package com.unibo.android.data.repository

import android.content.Context
import com.unibo.android.data.local.db.VaultDatabase
import com.unibo.android.data.local.entity.AccountEntryEntity
import com.unibo.android.domain.models.AccountEntryModel
import com.unibo.android.domain.repositories.AccountRepository

class AccountRepositoryImpl(context: Context) : AccountRepository {
    private val accountEntryDao = VaultDatabase.getInstance(context).accountEntryDao()

    override suspend fun createEntry(entry: AccountEntryModel) {
        accountEntryDao.insertEntry(
            AccountEntryEntity(
                title = entry.title,
                email = entry.email,
                password = entry.password,
                userId = entry.userId
            )
        )
    }

    override suspend fun getAllEntries(userId: Long): List<AccountEntryModel> {
        return accountEntryDao.getAllEntries(userId).map { entity ->
            AccountEntryModel(
                id = entity.id,
                title = entity.title,
                email = entity.email,
                password = entity.password,
                userId = entity.userId
            )
        }
    }

    override suspend fun deleteEntry(entry: AccountEntryModel) {
        accountEntryDao.deleteEntry(
            AccountEntryEntity(
                id = entry.id,
                title = entry.title,
                email = entry.email,
                password = entry.password,
                userId = entry.userId
            )
        )
    }

    override suspend fun updateEntry(entry: AccountEntryModel) {
        accountEntryDao.updateEntry(
            AccountEntryEntity(
                id = entry.id,
                title = entry.title,
                email = entry.email,
                password = entry.password,
                userId = entry.userId
            )
        )
    }
}

