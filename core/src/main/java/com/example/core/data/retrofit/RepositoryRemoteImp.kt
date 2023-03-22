package com.example.core.data.retrofit

import com.example.core.data.RepositoryRemote
import com.example.core.data.dto.CurrenciesDTO
import javax.inject.Inject

class RepositoryRemoteImp @Inject constructor(
    private val apiService: APIService
) : RepositoryRemote {

    override suspend fun getExchange(base: String): CurrenciesDTO {
        return apiService.getExchange(base)
    }
}