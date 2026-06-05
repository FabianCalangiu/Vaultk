package com.unibo.android.domain.repositories

interface IconRepository {
    suspend fun getIconUrl(domain: String): String
}