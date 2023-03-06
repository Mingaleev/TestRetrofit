package ru.mingaleev.testretrofit.data

import ru.mingaleev.testretrofit.data.dto.CurrenciesDTO

interface RepositoryRemote {
    suspend fun getExchange(base: String): CurrenciesDTO
}