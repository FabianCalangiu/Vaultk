package com.unibo.android.data.remote.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface HaveIBeenPwnedApi {
    @GET("range/{prefix}")
    suspend fun getHashRange(
        @Path("prefix") prefix: String
    ): ResponseBody
}