package ru.mingaleev.testretrofit.data.retrofit

import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.dto.CurrenciesDTO
import javax.inject.Inject

class RepositoryRemoteImp @Inject constructor(
    private val apiService: APIService
) : RepositoryRemote {

    override suspend fun getExchange(base: String): CurrenciesDTO {
        return apiService.getExchange(base)
    }
}