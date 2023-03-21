package ru.mingaleev.testretrofit.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Query
import ru.mingaleev.testretrofit.data.dto.CurrenciesDTO

interface APIService {
    @GET("latest")
    suspend fun getExchange(
        @Query("base") base: String
    ): CurrenciesDTO
}