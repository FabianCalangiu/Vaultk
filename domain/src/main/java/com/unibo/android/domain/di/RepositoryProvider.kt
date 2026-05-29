package com.unibo.android.domain.di

import com.unibo.android.domain.repositories.UserRepository

interface RepositoryProvider {
    val userRepository: UserRepository
}