package com.unibo.android.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {

        @Volatile
        private var INSTANCE: VaultDatabase? = null

        fun getInstance(
            context: Context
        ): VaultDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(

                    context.applicationContext,

                    VaultDatabase::class.java,

                    "vault_database"

                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}
