package com.example.favoruites.domain

import com.example.currency_data_api.entity.Currency


interface GetCurrenciesListRemoteUseCase {
    suspend operator fun invoke(rate: String): List<Currency>
}