package com.unibo.android.domain.repositories

interface DataBreachRepository {
    suspend fun checkPassword(prefix: String, suffix: String): Result<Boolean>
}