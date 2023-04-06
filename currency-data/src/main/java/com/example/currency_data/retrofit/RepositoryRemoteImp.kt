package com.example.currency_data.retrofit

import com.example.currency_data.mapper.CurrenciesMapper
import com.example.currency_data_api.RepositoryRemote
import com.example.currency_data_api.entity.CurrencyApi
import javax.inject.Inject

class RepositoryRemoteImp @Inject constructor(
    private val apiService: APIService,
    private val currenciesMapper: CurrenciesMapper
) : RepositoryRemote {

    override suspend fun getExchange(base: String): List<CurrencyApi> {
        val currenciesDTO = apiService.getExchange(base)
        return currenciesMapper.mapCurrenciesFromDTO(currenciesDTO)
    }
}