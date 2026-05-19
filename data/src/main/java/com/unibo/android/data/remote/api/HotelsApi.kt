package com.unibo.android.data.remote.api

import com.unibo.android.data.remote.models.metadata.MetaData
import com.unibo.android.data.remote.models.search.Search
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HotelsApi {

    @GET("v2/get-meta-data")
    suspend fun getMetadata(): MetaData

    @GET("locations/v3/search")
    suspend fun search(
        @Query("q") q: String,
        @Query("locale") locale: String,
        @Query("langid") langid: Int,
        @Query("siteid") siteId: Int,
    ): Search
}