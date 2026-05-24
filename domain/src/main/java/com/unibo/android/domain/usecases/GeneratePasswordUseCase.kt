package com.unibo.android.domain.usecases

import android.health.connect.datatypes.units.Length
import java.security.SecureRandom
import kotlin.math.log2

class GeneratePasswordUseCase {
    private val secureRandom = SecureRandom()

    operator fun invoke(
        length: Int,
        includeNumbers: Boolean,
        includeSpecialChars: Boolean,
        includeUppercase: Boolean,
        includeLowercase: Boolean
    ): GeneratedPasswordResult {
        require(length in 4..255) { "Password length must be between 4 and 255" }

        val lowercase = "abdcefghijklmnopqrstuvwxyz"
        val uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val numbers = "0123456789"
        val specialChars = "!@#$%^&*"

        val charsets = mutableListOf<String>()

        if (includeLowercase) charsets.add(lowercase)
        if (includeUppercase) charsets.add(uppercase)
        if (includeNumbers) charsets.add(numbers)
        if (includeSpecialChars) charsets.add(specialChars)

        require(charsets.isNotEmpty()) { "At least one character set must be selected" }

        val allAllowedChars = charsets.joinToString("")
        val passwordChars = mutableListOf<Char>()

        charsets.forEach { charset ->
            passwordChars.add(charset.randomChar())
        }

        repeat(length - passwordChars.size) {
            passwordChars.add(allAllowedChars.randomChar())
        }

        passwordChars.shuffleSecurely()

        val password = passwordChars.joinToString("")

        val entropyBits = calculateEntropyBits(
            charsetSize = allAllowedChars.length,
            length = length
        )

        return GeneratedPasswordResult(
            password = password,
            entropyBits = entropyBits
        )
    }

    private fun String.randomChar(): Char {
        return this[secureRandom.nextInt(this.length)]
    }

    private fun MutableList<Char>.shuffleSecurely() {
        for(i in lastIndex downTo 1) {
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
        return length * log2(charsetSize.toDouble())
    }
}

data class GeneratedPasswordResult(
    val password: String,
    val entropyBits: Double
)
