package com.example.currency_data.retrofit

import com.example.currency_data_api.dto.CurrenciesDTO
import com.example.currency_data_api.RepositoryRemote
import javax.inject.Inject

class RepositoryRemoteImp @Inject constructor(
    private val apiService: APIService
) : RepositoryRemote {

    override suspend fun getExchange(base: String): CurrenciesDTO {
        return apiService.getExchange(base)
    }
}