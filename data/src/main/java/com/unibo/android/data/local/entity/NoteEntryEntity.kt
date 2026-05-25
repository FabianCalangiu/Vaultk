package com.unibo.android.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_entries")
data class NoteEntryEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
)