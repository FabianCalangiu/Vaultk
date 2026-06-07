package com.unibo.android.domain.repositories

import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun saveUser(userId: Long, email: String)

    suspend fun saveUserEmail(email: String)

    suspend fun saveUserId(userId: Long)

    fun getUserId(): Flow<Long?>

    fun getUserEmail(): Flow<String?>

    suspend fun clearSession()
}