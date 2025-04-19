package com.example.ph55500.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    val api: CatApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.2:3000/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatApiService::class.java)
    }
}
