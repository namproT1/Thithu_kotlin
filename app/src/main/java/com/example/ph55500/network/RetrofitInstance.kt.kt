package com.example.ph55500.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: CatApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatApiService::class.java)
    }
}
