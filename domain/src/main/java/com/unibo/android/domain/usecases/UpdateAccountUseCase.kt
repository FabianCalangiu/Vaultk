package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.AccountEntryModel
import com.unibo.android.domain.repositories.AccountRepository
import com.unibo.android.domain.security.CryptoManager

interface UpdateAccountUseCase {
    suspend operator fun invoke(
        entry: AccountEntryModel
    ): Result<Unit>
}

class UpdateAccountUseCaseImpl(
    private val accountRepository: AccountRepository,
    private val cryptoManager: CryptoManager
) : UpdateAccountUseCase {
    override suspend operator fun invoke(entry: AccountEntryModel): Result<Unit> {
        val encryptedEntry = entry.copy(password = cryptoManager.encrypt(entry.password))

        return try {
            accountRepository.updateEntry(encryptedEntry)
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}