package com.unibo.android.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.unibo.android.data.local.entity.NoteEntryEntity

@Dao
interface NoteEntryDao {
    @Insert
    suspend fun createNote(entry: NoteEntryEntity)

    @Delete
    suspend fun deleteNote(entry: NoteEntryEntity)

    @Update
    suspend fun updateNote(entry: NoteEntryEntity)

    @Query("SELECT * FROM note_entries WHERE id = :id")
    suspend fun getNoteById(id: Long): NoteEntryEntity

    //USARE SESSION MANAGER O DATASTORE PER RECUPERARE UTENTE "LOGGATO"
    @Query("SELECT * FROM note_entries WHERE userId = :userId")
    suspend fun getAllNotes(userId: Long): List<NoteEntryEntity>
}