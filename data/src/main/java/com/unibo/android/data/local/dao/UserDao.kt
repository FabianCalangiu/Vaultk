package com.unibo.android.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.unibo.android.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun createUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("SELECT id FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserId(email: String): Long?

    @Query("SELECT password FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserPasswordByEmail(email: String): String?
}