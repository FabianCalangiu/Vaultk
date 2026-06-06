package com.unibo.android.domain.usecases

import java.security.SecureRandom
import kotlin.math.log2

interface GeneratePasswordUseCase {
    operator fun invoke(
        length: Int,
        includeNumbers: Boolean,
        includeSpecialChars: Boolean,
        includeUppercase: Boolean,
        includeLowercase: Boolean
    ): Result<GeneratedPasswordResult>
}

class GeneratePasswordUseCaseImpl : GeneratePasswordUseCase {

    private val secureRandom = SecureRandom()

    override operator fun invoke(
        length: Int,
        includeNumbers: Boolean,
        includeSpecialChars: Boolean,
        includeUppercase: Boolean,
        includeLowercase: Boolean
    ): Result<GeneratedPasswordResult> {

        if (length !in 4..255) {
            return Result.failure(
                IllegalArgumentException(
                    "Password length must be between 4 and 255"
                )
            )
        }

        val lowercase = "abcdefghijklmnopqrstuvwxyz"
        val uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val numbers = "0123456789"
        val specialChars = "!@#$%^&*"

        val charsets = mutableListOf<String>()

        if (includeLowercase) charsets.add(lowercase)
        if (includeUppercase) charsets.add(uppercase)
        if (includeNumbers) charsets.add(numbers)
        if (includeSpecialChars) charsets.add(specialChars)

        if (charsets.isEmpty()) {
            return Result.failure(
                IllegalArgumentException(
                    "Select at least one character set"
                )
            )
        }

        val allAllowedChars = charsets.joinToString("")
        val passwordChars = mutableListOf<Char>()

        charsets.forEach { charset ->
            passwordChars.add(charset.randomChar())
        }

        repeat(length - passwordChars.size) {
            passwordChars.add(
                allAllowedChars.randomChar()
            )
        }

        passwordChars.shuffleSecurely()

        val password = passwordChars.joinToString("")

        val entropyBits = calculateEntropyBits(
            charsetSize = allAllowedChars.length,
            length = length
        )

        return Result.success(
            GeneratedPasswordResult(
                password = password,
                entropyBits = entropyBits
            )
        )
    }

    private fun String.randomChar(): Char {
        return this[
            secureRandom.nextInt(this.length)
        ]
    }

    private fun MutableList<Char>.shuffleSecurely() {
        for (i in lastIndex downTo 1) {

            val j = secureRandom.nextInt(i + 1)

            val temp = this[i]
            this[i] = this[j]
            this[j] = temp
        }
    }

    private fun calculateEntropyBits(
        charsetSize: Int,
        length: Int
    ): Double {
        return length * log2(
            charsetSize.toDouble()
        )
    }
}

data class GeneratedPasswordResult(
    val password: String,
    val entropyBits: Double
)