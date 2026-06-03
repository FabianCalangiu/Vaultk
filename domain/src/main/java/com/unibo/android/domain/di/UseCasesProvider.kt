package com.unibo.android.domain.di

import com.unibo.android.domain.usecases.AddNoteEntryUseCase
import com.unibo.android.domain.usecases.AddNoteEntryUseCaseImpl
import com.unibo.android.domain.usecases.CreateAccountUseCase
import com.unibo.android.domain.usecases.CreateAccountUseCaseImpl
import com.unibo.android.domain.usecases.DeleteAccountUseCase
import com.unibo.android.domain.usecases.DeleteAccountUseCaseImpl
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
    lateinit var addNoteEntryUseCase: AddNoteEntryUseCase
    lateinit var getNotesUseCase: GetNotesUseCase
    lateinit var createAccountUseCase: CreateAccountUseCase
    lateinit var getAccountsUseCase: GetAccountsUseCase

    lateinit var deleteAccountUseCase: DeleteAccountUseCase

    fun setup(repositoryProvider: RepositoryProvider) {
        registerUseCase = RegisterUseCaseImpl(repositoryProvider.userRepository)
        loginUseCase = LoginUseCaseImpl(repositoryProvider.userRepository)
        sessionUseCase = SessionUseCaseImpl(repositoryProvider.sessionRepository, repositoryProvider.userRepository)
        addNoteEntryUseCase = AddNoteEntryUseCaseImpl(repositoryProvider.noteRepository, repositoryProvider.sessionRepository)
        getNotesUseCase = GetNotesUseCaseImpl(repositoryProvider.noteRepository, repositoryProvider.sessionRepository)
        createAccountUseCase = CreateAccountUseCaseImpl(repositoryProvider.accountRepository, repositoryProvider.sessionRepository)
        getAccountsUseCase = getAccountsUseCaseImpl(repositoryProvider.accountRepository, repositoryProvider.sessionRepository)
        deleteAccountUseCase = DeleteAccountUseCaseImpl(repositoryProvider.accountRepository)
    }
}