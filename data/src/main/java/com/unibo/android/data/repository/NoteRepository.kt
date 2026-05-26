package com.unibo.android.data.repository

import com.unibo.android.data.local.dao.NoteEntryDao
import com.unibo.android.data.local.entity.NoteEntryEntity

class NoteRepository(
    private val noteEntryDao: NoteEntryDao
) {

    suspend fun createNote(note: NoteEntryEntity) {
        noteEntryDao.createNote(note)
    }

    suspend fun deleteNote(note: NoteEntryEntity) {
        noteEntryDao.deleteNote(note)
    }

    suspend fun getAllNotes(userId: Long) {
        noteEntryDao.getAllNotes(userId)
    }
}