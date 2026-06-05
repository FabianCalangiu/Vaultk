package com.unibo.android.data.repository

import com.unibo.android.data.remote.RetrofitProvider
import com.unibo.android.domain.repositories.DataBreachRepository

class DataBreachRepositoryImpl : DataBreachRepository {
    override suspend fun checkPassword(
        prefix: String,
        suffix: String
    ): Result<Boolean> {
        return try {
            val response = RetrofitProvider.hibpApi.getHashRange(prefix)
            val body = response.string()

            val isPwned = body.lines().any { line ->
                line.split(":").firstOrNull() == suffix
            }

            Result.success(isPwned)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}