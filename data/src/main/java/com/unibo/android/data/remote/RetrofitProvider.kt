package com.unibo.android.data.remote

import com.unibo.android.data.remote.api.HaveIBeenPwnedApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitProvider {
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.pwnedpasswords.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val hibpApi: HaveIBeenPwnedApi = retrofit.create(HaveIBeenPwnedApi::class.java)
}