package com.unibo.android.data.repository

import android.content.Context
import com.unibo.android.data.local.db.VaultDatabase
import com.unibo.android.data.local.entity.NoteEntryEntity
import com.unibo.android.domain.models.NoteEntryModel
import com.unibo.android.domain.repositories.NoteRepository

class NoteRepositoryImpl(context: Context) : NoteRepository {
    private val noteEntryDao = VaultDatabase.getInstance(context).noteEntryDao()

    override suspend fun createNote(noteEntry: NoteEntryModel) {
        noteEntryDao.createNote(NoteEntryEntity(
            title = noteEntry.title,
            content = noteEntry.content,
            userId = noteEntry.userId)
        )
    }

    override suspend fun getAllNotes(userId: Long): List<NoteEntryModel> {
        return noteEntryDao.getAllNotes(userId).map { entity ->
            NoteEntryModel(
                id = entity.id,
                title = entity.title,
                content = entity.content,
                userId = entity.userId
            )
        }
    }
}