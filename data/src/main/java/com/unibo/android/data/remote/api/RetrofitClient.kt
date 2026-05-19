package com.unibo.android.data.remote.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient {
    private val baseUrl = "https://hotels4.p.rapidapi.com"

    private val headerInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("x-rapidapi-host", "hotels4.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "403bfc7b10mshcbc427f7386763ap193138jsn425304652615")
            .build()
        chain.proceed(request)
    }

    val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(headerInterceptor)
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofitClient = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val hotelsService : HotelsApi by lazy {
        retrofitClient.create(HotelsApi::class.java)
    }
}