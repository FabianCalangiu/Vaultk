package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.AccountEntryModel
import com.unibo.android.domain.repositories.AccountRepository

interface UpdateAccountUseCase {
    suspend operator fun invoke(
        entry: AccountEntryModel
    ): Result<Unit>
}

class UpdateAccountUseCaseImpl(
    private val accountRepository: AccountRepository
) : UpdateAccountUseCase {
    override suspend operator fun invoke(entry: AccountEntryModel): Result<Unit> {
        return try {
            accountRepository.updateEntry(entry)
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}