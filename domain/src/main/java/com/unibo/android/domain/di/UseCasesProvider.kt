package com.unibo.android.domain.di

import com.unibo.android.domain.usecases.RegisterUseCase
import com.unibo.android.domain.usecases.RegisterUseCaseImpl

object UseCasesProvider {
    lateinit var registerUseCase: RegisterUseCase

    fun setup(
        repositoryProvider: RepositoryProvider
    ) {
        registerUseCase = RegisterUseCaseImpl(
            userRepository = repositoryProvider.userRepository
        )
    }
}