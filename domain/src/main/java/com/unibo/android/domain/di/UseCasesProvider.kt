package com.unibo.android.domain.di

import com.unibo.android.domain.usecases.CreateAccountUseCase
import com.unibo.android.domain.usecases.CreateAccountUseCaseImpl
import com.unibo.android.domain.usecases.GetAccountsUseCase
import com.unibo.android.domain.usecases.LoginUseCase
import com.unibo.android.domain.usecases.LoginUseCaseImpl
import com.unibo.android.domain.usecases.RegisterUseCase
import com.unibo.android.domain.usecases.RegisterUseCaseImpl
import com.unibo.android.domain.usecases.SessionUseCase
import com.unibo.android.domain.usecases.SessionUseCaseImpl
import com.unibo.android.domain.usecases.getAccountsUseCaseImpl


object UseCasesProvider {
    lateinit var registerUseCase: RegisterUseCase
    lateinit var loginUseCase: LoginUseCase
    lateinit var sessionUseCase: SessionUseCase
    lateinit var createAccountUseCase: CreateAccountUseCase
    lateinit var getAccountsUseCase: GetAccountsUseCase

    fun setup(
        repositoryProvider: RepositoryProvider
    ) {
        registerUseCase = RegisterUseCaseImpl(repositoryProvider.userRepository)
        loginUseCase = LoginUseCaseImpl(repositoryProvider.userRepository)
        sessionUseCase = SessionUseCaseImpl(repositoryProvider.sessionRepository, repositoryProvider.userRepository)
        createAccountUseCase = CreateAccountUseCaseImpl(repositoryProvider.accountRepository, repositoryProvider.sessionRepository)
        getAccountsUseCase = getAccountsUseCaseImpl(repositoryProvider.accountRepository, repositoryProvider.sessionRepository)
    }
}