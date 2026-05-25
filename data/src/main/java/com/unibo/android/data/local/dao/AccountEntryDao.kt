package com.unibo.android.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.unibo.android.data.local.entity.AccountEntryEntity

@Dao
interface AccountEntryDao {
    @Insert
    suspend fun insertEntry(
        entry: AccountEntryEntity
    )

    @Query("SELECT * FROM account_entries")
    suspend fun getAllEntries(): List<AccountEntryEntity>
}