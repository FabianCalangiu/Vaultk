package com.unibo.android.data.repository

import com.unibo.android.data.remote.RetrofitProvider
import com.unibo.android.domain.repositories.IconRepository

class IconRepositoryImpl : IconRepository {
    private val api = RetrofitProvider.faviconApi

    override suspend fun getIconUrl(domain: String): String {
        val response = api.getFavicon(domain)

        if(!response.isSuccessful) {
            throw Exception("Unable to load favicon")
        }

        return "https://favicon.im/$domain?larger=true"
    }
}