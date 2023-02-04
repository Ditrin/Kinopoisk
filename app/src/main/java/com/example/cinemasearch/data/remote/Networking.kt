package com.example.cinemasearch.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Networking {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val contentType = "application/json".toMediaType()

    private val json = Json { ignoreUnknownKeys = true }

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://kinopoiskapiunofficial.tech")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val cinemaSearchAPI: CinemaSearchAPI
        get() = retrofit.create(CinemaSearchAPI::class.java)

}
