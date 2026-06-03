package com.unibo.android.domain.di

import com.unibo.android.domain.usecases.CreateAccountUseCase
import com.unibo.android.domain.usecases.CreateAccountUseCaseImpl
import com.unibo.android.domain.usecases.CreateNoteUseCase
import com.unibo.android.domain.usecases.CreateNoteUseCaseImpl
import com.unibo.android.domain.usecases.DeleteNoteUseCase
import com.unibo.android.domain.usecases.DeleteNoteUseCaseImpl
import com.unibo.android.domain.usecases.GetAccountsUseCase
import com.unibo.android.domain.usecases.GetNotesUseCase
import com.unibo.android.domain.usecases.GetNotesUseCaseImpl
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
    lateinit var createNoteUseCase: CreateNoteUseCase
    lateinit var getNotesUseCase: GetNotesUseCase
    lateinit var createAccountUseCase: CreateAccountUseCase
    lateinit var getAccountsUseCase: GetAccountsUseCase
    lateinit var deleteNoteEntry: DeleteNoteUseCase

    fun setup(repositoryProvider: RepositoryProvider) {
        registerUseCase = RegisterUseCaseImpl(repositoryProvider.userRepository)
        loginUseCase = LoginUseCaseImpl(repositoryProvider.userRepository)
        sessionUseCase = SessionUseCaseImpl(repositoryProvider.sessionRepository, repositoryProvider.userRepository)
        createNoteUseCase = CreateNoteUseCaseImpl(repositoryProvider.noteRepository, repositoryProvider.sessionRepository)
        getNotesUseCase = GetNotesUseCaseImpl(repositoryProvider.noteRepository, repositoryProvider.sessionRepository)
        createAccountUseCase = CreateAccountUseCaseImpl(repositoryProvider.accountRepository, repositoryProvider.sessionRepository)
        getAccountsUseCase = getAccountsUseCaseImpl(repositoryProvider.accountRepository, repositoryProvider.sessionRepository)
        deleteNoteEntry = DeleteNoteUseCaseImpl(repositoryProvider.noteRepository, repositoryProvider.sessionRepository)
    }
}