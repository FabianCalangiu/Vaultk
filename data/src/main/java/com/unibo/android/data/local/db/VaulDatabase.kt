package com.unibo.android.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unibo.android.data.local.dao.AccountEntryDao
import com.unibo.android.data.local.entity.AccountEntryEntity

@Database(
    entities = [
        AccountEntryEntity::class,
        NoteEntryEntity::class
    ],
    version = 1
)
abstract class VaultDatabase : RoomDatabase() {
    abstract fun accountEntryDao(): AccountEntryDao
    abstract fun noteEntryDao(): NoteEntryDao
}
