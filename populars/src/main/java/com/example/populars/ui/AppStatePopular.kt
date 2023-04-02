package com.example.populars.ui

import com.example.currency_data_api.entity.CurrencyApi


sealed class AppStatePopular {
    data class SuccessListExchange(val currenciesList: List<CurrencyApi>) : AppStatePopular()
    data class Error(val error: Throwable) : AppStatePopular()
}