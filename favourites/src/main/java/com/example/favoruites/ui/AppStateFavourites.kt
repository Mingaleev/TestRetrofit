package com.example.favoruites.ui

import com.example.currency_data_api.entity.Currency


sealed class AppStateFavourites {
    data class SuccessListExchange(val currenciesList: List<Currency>, val listForSpinner: List<String>) : AppStateFavourites()
    data class Error(val error: Throwable) : AppStateFavourites()
}