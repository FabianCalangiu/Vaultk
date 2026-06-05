package com.unibo.android.domain.usecases

import com.unibo.android.domain.repositories.DataBreachRepository
import java.security.MessageDigest

interface CheckPasswordBreachUseCase {
    suspend operator fun invoke(
        password: String
    ): Result<Boolean>
}

class CheckPasswordBreachUseCaseImpl(
    val dataBreachRepository: DataBreachRepository
) : CheckPasswordBreachUseCase {
    override suspend operator fun invoke(password: String): Result<Boolean> {
        val sha1 = MessageDigest.getInstance("SHA-1")
            .digest(password.toByteArray())
            .joinToString("") { "%02X".format(it) }

        val prefix = sha1.take(5)
        val suffix = sha1.drop(5)

        return dataBreachRepository.checkPassword(prefix, suffix)
    }
}