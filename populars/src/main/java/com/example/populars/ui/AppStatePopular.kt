package com.example.populars.ui

import com.example.core.entity.Currency

sealed class AppStatePopular {
    data class SuccessListExchange(val currenciesList: List<Currency>) : AppStatePopular()
    data class Error(val error: Throwable) : AppStatePopular()
}