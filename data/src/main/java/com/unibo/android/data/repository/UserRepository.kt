package com.unibo.android.data.repository

import com.unibo.android.data.local.dao.UserDao
import com.unibo.android.data.local.entity.UserEntity

class UserRepository(
    private val userEntryDao: UserDao
) {

    suspend fun createUser(user: UserEntity) {
        userEntryDao.createUser(user)
    }

    suspend fun deleteUser(user: UserEntity) {
        userEntryDao.deleteUser(user)
    }

    suspend fun getUserId(email: String) {
        userEntryDao.getUserId(email)
    }
}