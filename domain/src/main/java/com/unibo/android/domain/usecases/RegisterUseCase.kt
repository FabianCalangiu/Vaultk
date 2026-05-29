package com.unibo.android.domain.usecases

import android.util.Patterns
import com.unibo.android.domain.models.UserModel
import com.unibo.android.domain.repositories.UserRepository
import org.mindrot.jbcrypt.BCrypt

interface RegisterUseCase {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit>
}

class RegisterUseCaseImpl(
    private val userRepository: UserRepository
) : RegisterUseCase {

    override suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            if (!isValidEmail(email)) {

                return Result.failure(
                    Exception("Invalid email format")
                )
            }

            if (!isValidPassword(password)) {

                return Result.failure(
                    Exception(
                        "Password must contain at least:\n" +
                                "- 8 characters\n" +
                                "- 1 uppercase letter\n" +
                                "- 1 lowercase letter\n" +
                                "- 1 number\n" +
                                "- 1 special character"
                    )
                )
            }

            val existingUserId = userRepository.getUserId(email)

            if (existingUserId != null) {
                return Result.failure(Exception("User already exists"))
            }

            val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12))

            val user = UserModel(
                email = email,
                password = hashedPassword
            )

            userRepository.createUser(user)

            Result.success(Unit)

        } catch (exception: Exception) {

            Result.failure(exception)
        }
    }

    private fun isValidEmail(
        email: String
    ): Boolean {

        return Patterns.EMAIL_ADDRESS
            .matcher(email)
            .matches()
    }

    private fun isValidPassword(
        password: String
    ): Boolean {

        val passwordRegex = Regex(

            pattern =
                "^(?=.*[a-z])" +
                        "(?=.*[A-Z])" +
                        "(?=.*\\d)" +
                        "(?=.*[@$!%*?&])" +
                        "[A-Za-z\\d@$!%*?&]{8,}$"
        )

        return passwordRegex.matches(password)
    }
}