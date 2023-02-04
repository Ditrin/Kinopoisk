package com.example.cinemasearch.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AddApiKey : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
        val originalRequest = chain.request()

        val request = if (token != null) {
            originalRequest.newBuilder()
                .header("X-API-KEY", "$token")
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(request)
    }
}