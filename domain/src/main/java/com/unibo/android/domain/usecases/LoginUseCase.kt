package com.unibo.android.domain.usecases

import com.unibo.android.domain.repositories.UserRepository
import org.mindrot.jbcrypt.BCrypt

interface LoginUseCase {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Boolean
}

class LoginUseCaseImpl(
    private val userRepository: UserRepository
) : LoginUseCase {

    override suspend operator fun invoke(
        email: String,
        password: String
    ): Boolean {

        val hashedPassword = userRepository.getUserPasswordByEmail(email)

        if (hashedPassword == null) {
            return false
        }

        return BCrypt.checkpw(password, hashedPassword)
    }
}