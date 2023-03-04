package ru.mingaleev.testretrofit.ui.popular

import ru.mingaleev.testretrofit.data.dto.Currency

sealed class AppStatePopular {
    data class SuccessListExchange(val currenciesList: List<Currency>) : AppStatePopular()
    data class Error(val error: Throwable) : AppStatePopular()
}