package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.AccountEntryModel
import com.unibo.android.domain.repositories.AccountRepository

interface DeleteAccountUseCase {
    suspend operator fun invoke(
        entry: AccountEntryModel
    ): Result<Unit>
}

class DeleteAccountUseCaseImpl(
    private val accountRepository: AccountRepository
) : DeleteAccountUseCase {
    override suspend operator fun invoke(
        entry: AccountEntryModel
    ): Result<Unit> {
        return try {
            accountRepository.deleteEntry(entry)
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}