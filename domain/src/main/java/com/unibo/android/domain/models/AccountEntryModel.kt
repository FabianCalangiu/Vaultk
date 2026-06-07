package com.unibo.android.domain.models

data class AccountEntryModel (
    val id: Long,
    val title: String,
    val email: String,
    val website: String,
    val password: String,
    val userId: Long?
)