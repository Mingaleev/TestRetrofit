package ru.mingaleev.testretrofit.ui.popular

import ru.mingaleev.testretrofit.domain.entity.Currency

sealed class AppStatePopular {
    data class SuccessListExchange(val currenciesList: List<Currency>) : AppStatePopular()
    data class Error(val error: Throwable) : AppStatePopular()
}