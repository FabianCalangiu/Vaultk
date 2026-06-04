package com.unibo.android.data.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import android.util.Base64
import com.unibo.android.domain.security.CryptoManager
import javax.crypto.spec.GCMParameterSpec

class CryptoManagerImpl : CryptoManager {
    private val keyAlias = "vaultk_secret_key"

    private val transformation = "AES/GCM/NoPadding"

    private val androidKeyStore = "AndroidKeyStore"

    private fun getOrCreateSecretKey(): SecretKey {
        val keystore = KeyStore.getInstance(androidKeyStore)
        keystore.load(null)

        val existingKey = keystore.getKey(keyAlias, null)

        if(existingKey != null) {
            return existingKey as SecretKey
        }

        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, androidKeyStore)

        val keySpec = KeyGenParameterSpec.Builder(
            keyAlias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .build()

        keyGenerator.init(keySpec)

        return keyGenerator.generateKey()
    }

    override fun encrypt(plainText: String): String {
        val cipher = Cipher.getInstance(transformation)

        cipher.init(
            Cipher.ENCRYPT_MODE,
            getOrCreateSecretKey()
        )

        val iv = cipher.iv

        val encryptedBytes = cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))

        val combined = iv + encryptedBytes

        return Base64.encodeToString(combined, Base64.NO_WRAP)
    }

    override fun decrypt(encryptedText: String): String {
        val combined = Base64.decode(encryptedText, Base64.NO_WRAP)

        val iv = combined.copyOfRange(0, 12)

        val encryptedBytes = combined.copyOfRange(12, combined.size)

        val cipher = Cipher.getInstance(transformation)

        cipher.init(Cipher.DECRYPT_MODE, getOrCreateSecretKey(), GCMParameterSpec(128, iv))

        val decryptedBytes = cipher.doFinal(encryptedBytes)

        return decryptedBytes.toString(Charsets.UTF_8)
    }
}