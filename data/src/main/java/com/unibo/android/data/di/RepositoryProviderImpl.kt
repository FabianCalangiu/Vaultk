package com.unibo.android.data.di

import android.content.Context
import com.unibo.android.data.repository.UserRepositoryImpl
import com.unibo.android.domain.di.RepositoryProvider
import com.unibo.android.domain.repositories.UserRepository

class RepositoryProviderImpl(
    context: Context
) : RepositoryProvider {
    override val userRepository: UserRepository = UserRepositoryImpl(
        context = context
    )
}