package com.example.ph55500.network
import com.example.ph55500.model.Cat
import retrofit2.http.GET

interface CatApiService {
    @GET("cats")
    suspend fun getCats(): List<Cat>
}
