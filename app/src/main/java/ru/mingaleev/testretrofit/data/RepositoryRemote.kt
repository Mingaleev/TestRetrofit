package ru.mingaleev.testretrofit.data

import ru.mingaleev.testretrofit.data.dto.CurrenciesDTO
import ru.mingaleev.testretrofit.data.dto.Rate

interface RepositoryRemote {
    suspend fun getExchange(base: String):
            List<Rate>
}