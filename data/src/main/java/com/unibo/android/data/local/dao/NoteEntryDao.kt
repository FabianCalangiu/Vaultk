package com.unibo.android.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.unibo.android.data.local.entity.NoteEntryEntity

@Dao
interface NoteEntryDao {
    @Insert
    suspend fun insertNote(entry: NoteEntryEntity)

    @Delete
    suspend fun deleteNote(entry: NoteEntryEntity)

    //USARE SESSION MANAGER O DATASTORE PER RECUPERARE UTENTE "LOGGATO"
    @Query("SELECT * FROM note_entries WHERE user_id = :userId")
    suspend fun getAllNotes(userId: Long): List<NoteEntryEntity>
}