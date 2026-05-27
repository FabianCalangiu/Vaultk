package com.unibo.android.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unibo.android.data.local.dao.AccountEntryDao
import com.unibo.android.data.local.dao.NoteEntryDao
import com.unibo.android.data.local.dao.UserDao
import com.unibo.android.data.local.entity.AccountEntryEntity
import com.unibo.android.data.local.entity.NoteEntryEntity
import com.unibo.android.data.local.entity.UserEntity

@Database(
    entities = [
        AccountEntryEntity::class,
        NoteEntryEntity::class,
        UserEntity::class
    ],
    version = 1
)
abstract class VaultDatabase : RoomDatabase() {
    abstract fun accountEntryDao(): AccountEntryDao
    abstract fun noteEntryDao(): NoteEntryDao
    abstract fun userDao(): UserDao
}
