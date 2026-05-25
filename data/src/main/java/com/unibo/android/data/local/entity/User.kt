package com.unibo.android.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val email: String,
    val password: String
)