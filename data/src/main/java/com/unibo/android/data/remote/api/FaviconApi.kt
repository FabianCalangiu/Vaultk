package com.unibo.android.data.remote.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FaviconApi {
    @GET("{domain}")
    suspend fun getFavicon(
        @Path("domain") domain: String,
        @Query("larger") larger: Boolean = true
    ): Response<ResponseBody>
}