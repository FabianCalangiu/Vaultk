package com.unibo.android.domain.models

data class NoteEntryModel (
    val id: Long = 0,
    val title: String,
    val content: String,
    val userId: Long
)