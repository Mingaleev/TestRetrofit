package com.example.core.data.retrofit

import com.example.core.data.dto.CurrenciesDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("latest")
    suspend fun getExchange(
        @Query("base") base: String
    ): CurrenciesDTO
}