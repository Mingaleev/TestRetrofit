package com.example.favoruites.domain.interactor

import com.example.currency_data_api.entity.CurrencyApi
import com.example.favoruites.domain.SortedAlphabetCurrenciesListUseCase
import javax.inject.Inject

class SortedAlphabetCurrenciesListUseCaseImp @Inject constructor(private val currenciesList: List<CurrencyApi>) :
    SortedAlphabetCurrenciesListUseCase {
    override suspend operator fun invoke(): List<CurrencyApi> {
        return currenciesList.sortedBy { currency -> currency.name }
    }
}


