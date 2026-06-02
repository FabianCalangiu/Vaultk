package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.AccountEntryModel
import com.unibo.android.domain.repositories.AccountRepository
import com.unibo.android.domain.repositories.SessionRepository
import kotlinx.coroutines.flow.first

interface GetAccountsUseCase {
    suspend operator fun invoke(): List<AccountEntryModel>
}

class getAccountsUseCaseImpl(
    private val accountRepository: AccountRepository,
    private val sessionRepository: SessionRepository
) : GetAccountsUseCase {
    override suspend operator fun invoke(): List<AccountEntryModel> {
        val userId = sessionRepository.getUserId().first()?: return emptyList()

        return accountRepository.getAllEntries(userId)
    }
}