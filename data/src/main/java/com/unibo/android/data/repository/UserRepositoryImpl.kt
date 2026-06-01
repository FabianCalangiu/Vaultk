package com.unibo.android.data.repository

import android.content.Context
import com.unibo.android.data.local.db.VaultDatabase
import com.unibo.android.data.local.entity.UserEntity
import com.unibo.android.data.session.SessionManager
import com.unibo.android.domain.models.UserModel
import com.unibo.android.domain.repositories.UserRepository

class UserRepositoryImpl(
    context: Context
) : UserRepository {

    private val userDao = VaultDatabase.getInstance(context).userDao()
    private val sessionManager: SessionManager = SessionManager(context)

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

        sessionManager.saveUserEmail(user.email)

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
        email: String
    ): Long {



        return userDao.getUserId(email)
    }

    override suspend fun getUserPasswordByEmail(
        email: String
    ): String? {
        return userDao.getUserPasswordByEmail(email)
    }
}