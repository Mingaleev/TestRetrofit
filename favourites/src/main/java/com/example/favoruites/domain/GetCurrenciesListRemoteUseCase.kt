package com.example.favoruites.domain

import com.example.currency_data_api.entity.CurrencyApi


interface GetCurrenciesListRemoteUseCase {
    suspend operator fun invoke(rate: String): List<CurrencyApi>
}