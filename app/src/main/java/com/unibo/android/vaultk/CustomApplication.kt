package com.unibo.android.vaultk

import android.app.Application
import androidx.room.Room
import com.unibo.android.data.local.database.VaultDatabase

class CustomApplication: Application() {
    lateinit var database: VaultDatabase

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            VaultDatabase::class.java,
            "vault_database"
        ).build()
    }
}