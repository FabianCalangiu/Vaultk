package com.unibo.android.domain.repositories

import com.unibo.android.domain.models.UserModel

interface UserRepository {
    suspend fun createUser(user: UserModel)
    suspend fun deleteUser(user: UserModel)
    suspend fun getUserId(user: UserModel): Long?
    suspend fun getUserPasswordByEmail(email: String): String?
}