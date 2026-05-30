package com.unibo.android.domain.di

import com.unibo.android.domain.usecases.LoginUseCase
import com.unibo.android.domain.usecases.LoginUseCaseImpl
import com.unibo.android.domain.usecases.RegisterUseCase
import com.unibo.android.domain.usecases.RegisterUseCaseImpl

object UseCasesProvider {
    lateinit var registerUseCase: RegisterUseCase
    lateinit var loginUseCase: LoginUseCase

    fun setup(
        repositoryProvider: RepositoryProvider
    ) {
        registerUseCase = RegisterUseCaseImpl(repositoryProvider.userRepository)

        loginUseCase = LoginUseCaseImpl(repositoryProvider.userRepository)
    }
}