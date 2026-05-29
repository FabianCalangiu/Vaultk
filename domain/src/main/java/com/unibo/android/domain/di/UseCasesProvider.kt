package com.unibo.android.domain.di

import com.unibo.android.domain.usecases.RegisterUseCase

object UseCasesProvider {
    lateinit var registerUseCase: RegisterUseCase

    fun setup(
        repositoryProvider: RepositoryProvider
    ) {
        registerUseCase = RegisterUseCase(
            userRepository = repositoryProvider.userRepository
        )
    }
}