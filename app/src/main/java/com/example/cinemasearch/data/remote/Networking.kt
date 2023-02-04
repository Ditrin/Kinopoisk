package com.example.cinemasearch.data.remote

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Networking {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-API-KEY","e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
            val originalHttpUrl = chain.request().url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("type", "TOP_100_POPULAR_FILMS")
                .build()
            request.url(url)
            val response = chain.proceed(request.build())
            return@addInterceptor response}
        .build()

    private val contentType = "application/json".toMediaType()

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://kinopoiskapiunofficial.tech")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val CinemaSearchAPI: CinemaSearchAPI
        get() = retrofit.create(CinemaSearchAPI::class.java)
}
