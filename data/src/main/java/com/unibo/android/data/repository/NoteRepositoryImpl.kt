package com.unibo.android.data.repository

import android.content.Context
import com.unibo.android.data.local.db.VaultDatabase
import com.unibo.android.domain.repositories.NoteRepository

class NoteRepositoryImpl(context: Context) : NoteRepository {
    private val noteEntryDao = VaultDatabase.getInstance(context).noteEntryDao()

}