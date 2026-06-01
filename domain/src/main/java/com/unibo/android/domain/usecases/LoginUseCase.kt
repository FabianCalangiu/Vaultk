package com.unibo.android.domain.usecases

import com.unibo.android.domain.repositories.UserRepository
import org.mindrot.jbcrypt.BCrypt

interface LoginUseCase {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit>
}

class LoginUseCaseImpl(
    private val userRepository: UserRepository
) : LoginUseCase {

    override suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> {

        val hashedPassword = userRepository.getUserPasswordByEmail(email)

        if (hashedPassword == null) {
            return Result.failure(Exception("User not found"))
        }

        if(BCrypt.checkpw(password, hashedPassword)) {
            return Result.success(Unit)
        } else {
            return Result.failure(Exception("Invalid password"))
        }
    }
}