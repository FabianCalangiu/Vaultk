package com.unibo.android.domain.security

interface CryptoManager {
    fun encrypt(plainText: String): String
    fun decrypt(encryptedText: String): String
}