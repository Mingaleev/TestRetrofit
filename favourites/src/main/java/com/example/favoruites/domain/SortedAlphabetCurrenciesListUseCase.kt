package com.example.favoruites.domain

import com.example.currency_data_api.entity.CurrencyApi

interface SortedAlphabetCurrenciesListUseCase {
    suspend operator fun invoke(): List<CurrencyApi>
}