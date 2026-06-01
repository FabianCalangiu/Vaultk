package com.unibo.android.domain.di

import com.unibo.android.domain.usecases.LoginUseCase
import com.unibo.android.domain.usecases.LoginUseCaseImpl
import com.unibo.android.domain.usecases.RegisterUseCase
import com.unibo.android.domain.usecases.RegisterUseCaseImpl
import com.unibo.android.domain.usecases.SessionUseCase
import com.unibo.android.domain.usecases.SessionUseCaseImpl



object UseCasesProvider {
    lateinit var registerUseCase: RegisterUseCase
    lateinit var loginUseCase: LoginUseCase
    lateinit var sessionUseCase: SessionUseCase

    fun setup(
        repositoryProvider: RepositoryProvider
    ) {
        registerUseCase = RegisterUseCaseImpl(repositoryProvider.userRepository)
        loginUseCase = LoginUseCaseImpl(repositoryProvider.userRepository)
        sessionUseCase = SessionUseCaseImpl(repositoryProvider.sessionRepository, repositoryProvider.userRepository)
    }
}