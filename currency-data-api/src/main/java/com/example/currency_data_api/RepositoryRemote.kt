package com.example.currency_data_api

import com.example.currency_data_api.dto.CurrenciesDTO

interface RepositoryRemote {
    suspend fun getExchange(base: String): CurrenciesDTO
}