package ru.mingaleev.testretrofit.ui.favoruites

import ru.mingaleev.testretrofit.domain.entity.Currency

sealed class AppStateFavourites {
    data class SuccessListExchange(val currenciesList: List<Currency>, val listForSpinner: List<String>) : AppStateFavourites()
    data class Error(val error: Throwable) : AppStateFavourites()
}