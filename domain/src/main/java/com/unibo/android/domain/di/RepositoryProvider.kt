package com.unibo.android.domain.di

import com.unibo.android.domain.repositories.AccountRepository
import com.unibo.android.domain.repositories.NoteRepository
import com.unibo.android.domain.repositories.SessionRepository
import com.unibo.android.domain.repositories.UserRepository
import com.unibo.android.domain.security.CryptoManager

interface RepositoryProvider {
    val userRepository: UserRepository
    val sessionRepository: SessionRepository
    val accountRepository: AccountRepository
    val noteRepository: NoteRepository

    val cryptoManager: CryptoManager
}