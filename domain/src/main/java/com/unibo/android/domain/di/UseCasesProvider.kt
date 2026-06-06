package com.unibo.android.domain.di

import com.unibo.android.domain.usecases.CheckPasswordBreachUseCase
import com.unibo.android.domain.usecases.CheckPasswordBreachUseCaseImpl
import com.unibo.android.domain.usecases.CreateAccountUseCase
import com.unibo.android.domain.usecases.CreateAccountUseCaseImpl
import com.unibo.android.domain.usecases.DeleteAccountUseCase
import com.unibo.android.domain.usecases.DeleteAccountUseCaseImpl
import com.unibo.android.domain.usecases.CreateNoteUseCase
import com.unibo.android.domain.usecases.CreateNoteUseCaseImpl
import com.unibo.android.domain.usecases.DeleteNoteUseCase
import com.unibo.android.domain.usecases.DeleteNoteUseCaseImpl
import com.unibo.android.domain.usecases.GeneratePasswordUseCase
import com.unibo.android.domain.usecases.GeneratePasswordUseCaseImpl
import com.unibo.android.domain.usecases.GetAccountsUseCase
import com.unibo.android.domain.usecases.GetIconUseCase
import com.unibo.android.domain.usecases.GetIconUseCaseImpl
import com.unibo.android.domain.usecases.GetNotesUseCase
import com.unibo.android.domain.usecases.GetNotesUseCaseImpl
import com.unibo.android.domain.usecases.LoginUseCase
import com.unibo.android.domain.usecases.LoginUseCaseImpl
import com.unibo.android.domain.usecases.RegisterUseCase
import com.unibo.android.domain.usecases.RegisterUseCaseImpl
import com.unibo.android.domain.usecases.SessionUseCase
import com.unibo.android.domain.usecases.SessionUseCaseImpl
import com.unibo.android.domain.usecases.UpdateAccountUseCase
import com.unibo.android.domain.usecases.UpdateAccountUseCaseImpl
import com.unibo.android.domain.usecases.UpdateNoteUseCase
import com.unibo.android.domain.usecases.UpdateNoteUseCaseImpl

import com.unibo.android.domain.usecases.getAccountsUseCaseImpl


object UseCasesProvider {
    lateinit var registerUseCase: RegisterUseCase
    lateinit var loginUseCase: LoginUseCase
    lateinit var sessionUseCase: SessionUseCase
    lateinit var createNoteUseCase: CreateNoteUseCase
    lateinit var getNotesUseCase: GetNotesUseCase
    lateinit var createAccountUseCase: CreateAccountUseCase
    lateinit var getAccountsUseCase: GetAccountsUseCase

    lateinit var deleteAccountUseCase: DeleteAccountUseCase
    lateinit var deleteNoteUseCase: DeleteNoteUseCase

    lateinit var updateAccountUseCase: UpdateAccountUseCase
    lateinit var updateNoteUseCase: UpdateNoteUseCase
    lateinit var checkPasswordBreachUseCase: CheckPasswordBreachUseCase

    lateinit var getIconUseCase: GetIconUseCase

    lateinit var generatePasswordUseCase: GeneratePasswordUseCase

    fun setup(repositoryProvider: RepositoryProvider) {
        registerUseCase = RegisterUseCaseImpl(repositoryProvider.userRepository)
        loginUseCase = LoginUseCaseImpl(repositoryProvider.userRepository)
        sessionUseCase = SessionUseCaseImpl(repositoryProvider.sessionRepository, repositoryProvider.userRepository)
        createNoteUseCase = CreateNoteUseCaseImpl(repositoryProvider.noteRepository, repositoryProvider.sessionRepository, repositoryProvider.cryptoManager)
        getNotesUseCase = GetNotesUseCaseImpl(repositoryProvider.noteRepository, repositoryProvider.sessionRepository, repositoryProvider.cryptoManager)
        createAccountUseCase = CreateAccountUseCaseImpl(repositoryProvider.accountRepository, repositoryProvider.sessionRepository, repositoryProvider.cryptoManager)
        getAccountsUseCase = getAccountsUseCaseImpl(repositoryProvider.accountRepository, repositoryProvider.sessionRepository, repositoryProvider.cryptoManager)
        deleteNoteUseCase = DeleteNoteUseCaseImpl(repositoryProvider.noteRepository)
        deleteAccountUseCase = DeleteAccountUseCaseImpl(repositoryProvider.accountRepository)
        updateAccountUseCase = UpdateAccountUseCaseImpl(repositoryProvider.accountRepository, repositoryProvider.cryptoManager)
        updateNoteUseCase = UpdateNoteUseCaseImpl(repositoryProvider.noteRepository, repositoryProvider.cryptoManager)
        checkPasswordBreachUseCase = CheckPasswordBreachUseCaseImpl(repositoryProvider.dataBreachRepository)
        getIconUseCase = GetIconUseCaseImpl(repositoryProvider.iconRepository)
        generatePasswordUseCase = GeneratePasswordUseCaseImpl()
    }
}