package com.unibo.android.domain.di

import com.unibo.android.domain.repositories.AccountRepository
import com.unibo.android.domain.repositories.NoteRepository
import com.unibo.android.domain.repositories.SessionRepository
import com.unibo.android.domain.repositories.UserRepository

interface RepositoryProvider {
    val userRepository: UserRepository
    val sessionRepository: SessionRepository
    val accountRepository: AccountRepository
    val noteRepository: NoteRepository
}