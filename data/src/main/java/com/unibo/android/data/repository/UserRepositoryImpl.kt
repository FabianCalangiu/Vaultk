package com.unibo.android.data.repository

import com.unibo.android.data.local.dao.UserDao
import com.unibo.android.data.local.entity.UserEntity

import com.unibo.android.domain.models.UserModel
import com.unibo.android.domain.repositories.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun createUser(
        user: UserModel
    ) {

        userDao.createUser(

            UserEntity(
                id = user.id,
                email = user.email,
                password = user.password
            )
        )
    }

    override suspend fun deleteUser(
        user: UserModel
    ) {

        userDao.deleteUser(

            UserEntity(
                id = user.id,
                email = user.email,
                password = user.password
            )
        )
    }

    override suspend fun getUserId(
        user: UserModel
    ): Long? {

        return userDao.getUserId(
            user.email
        )
    }

    override suspend fun getUserPasswordByEmail(
        email: String
    ): String? {

        return userDao.getUserPasswordByEmail(
            email
        )
    }
}