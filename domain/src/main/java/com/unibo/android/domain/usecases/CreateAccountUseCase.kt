package com.unibo.android.domain.usecases

import com.unibo.android.domain.models.AccountEntryModel
import com.unibo.android.domain.repositories.AccountRepository
import com.unibo.android.domain.repositories.SessionRepository
import com.unibo.android.domain.security.CryptoManager
import kotlinx.coroutines.flow.first

interface CreateAccountUseCase {
    suspend operator fun invoke(
        title: String,
        email: String,
        password: String
    ): Result<Unit>
}

class CreateAccountUseCaseImpl(
    private val accountRepository: AccountRepository,
    private val sessionRepository: SessionRepository,
    private val cryptoManager: CryptoManager
) : CreateAccountUseCase {
    override suspend operator fun invoke(
        title: String,
        email: String,
        password: String
    ): Result<Unit> {
        val NULL: Long = 0

        try {
            val userId = sessionRepository.getUserId().first()
            if (userId == NULL) {
                return Result.failure(Exception("User not logged in"))
            }

            if(title.isBlank() || email.isBlank() || password.isBlank()) {
                return Result.failure(Exception("Cannot contains empty values"))
            }

            accountRepository.createEntry(
                AccountEntryModel(
                    id = 0,
                    title = title,
                    email = email,
                    password = password,
                    userId = userId
                )
            )

            return Result.success(Unit)
        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }
}