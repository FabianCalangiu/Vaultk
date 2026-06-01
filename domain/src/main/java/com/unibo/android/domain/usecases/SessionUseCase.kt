package com.unibo.android.domain.usecases

import com.unibo.android.domain.repositories.SessionRepository
import com.unibo.android.domain.repositories.UserRepository

interface SessionUseCase {

    suspend operator fun invoke(
        email: String,
        func: String
    ): Result<Unit>
}

class SessionUseCaseImpl(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository
) : SessionUseCase {

    override suspend operator fun invoke(
        email: String,
        func: String
    ): Result<Unit> {
        when (func) {
            "register" -> {
                val id = userRepository.getUserId(email)

                if (id == null) {
                    return Result.failure(
                        Exception("User not found")
                    )
                }

                sessionRepository.saveUser(id, email)
            }
            "login" -> {
                val id = userRepository.getUserId(email)

                sessionRepository.saveUser(id, email)
            }
            "logout" -> {
                sessionRepository.clearSession()
            } else -> {
                return Result.failure(
                    Exception("something went wrong")
                )
            }
        }
        return Result.success(Unit)
    }
}