package com.example.core.data

import com.example.core.data.dto.CurrenciesDTO

interface RepositoryRemote {
    suspend fun getExchange(base: String): CurrenciesDTO
}