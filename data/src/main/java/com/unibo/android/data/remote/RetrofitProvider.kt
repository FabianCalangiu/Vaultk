package com.unibo.android.data.remote

import com.unibo.android.data.remote.api.HaveIBeenPwnedApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.unibo.android.data.remote.api.FaviconApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitProvider {
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val hibpRetrofit = Retrofit.Builder()
        .baseUrl("https://api.pwnedpasswords.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val faviconRetrofit = Retrofit.Builder()
        .baseUrl("https://favicon.im/")
        .build()

    val hibpApi: HaveIBeenPwnedApi = hibpRetrofit.create(HaveIBeenPwnedApi::class.java)
    val faviconApi: FaviconApi = faviconRetrofit.create(FaviconApi::class.java)
}
