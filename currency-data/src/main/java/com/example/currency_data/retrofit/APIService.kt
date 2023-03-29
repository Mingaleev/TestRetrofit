package com.example.currency_data.retrofit

import com.example.currency_data_api.dto.CurrenciesDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("latest")
    suspend fun getExchange(
        @Query("base") base: String
    ): CurrenciesDTO
}