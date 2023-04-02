package com.example.currency_data.mapper

import com.example.currency_data.dto.CurrenciesDTO
import com.example.currency_data_api.entity.CurrencyApi
import javax.inject.Inject

class CurrenciesMapper @Inject constructor() {

    fun mapCurrencies (from: CurrenciesDTO) : List<CurrencyApi> {
         return from.currencies.toList().map{ CurrencyApi(it.first, it.second) }
    }
}