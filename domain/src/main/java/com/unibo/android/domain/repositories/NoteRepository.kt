package com.unibo.android.domain.repositories

import com.unibo.android.domain.models.NoteEntryModel

interface NoteRepository {
    suspend fun createNote(noteEntry: NoteEntryModel)

    suspend fun deleteNote(id: Long)

    suspend fun getAllNotes(userId: Long)
}