package com.unibo.android.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.unibo.android.data.local.entity.AccountEntryEntity

@Dao
interface AccountEntryDao {
    @Insert
    suspend fun insertEntry(entry: AccountEntryEntity)

    @Delete
    suspend fun deleteEntry(entry: AccountEntryEntity)

    @Update
    suspend fun updateEntry(entry: AccountEntryEntity)

    @Query("SELECT * FROM account_entries WHERE userId = :userId ORDER BY title COLLATE NOCASE")
    suspend fun getAllEntries(userId: Long): List<AccountEntryEntity>
}