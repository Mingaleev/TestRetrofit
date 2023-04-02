package com.example.favoruites.ui

import com.example.currency_data_api.entity.CurrencyApi


sealed class AppStateFavourites {
    data class SuccessListExchange(val currenciesList: List<CurrencyApi>, val listForSpinner: List<String>) : AppStateFavourites()
    data class Error(val error: Throwable) : AppStateFavourites()
}