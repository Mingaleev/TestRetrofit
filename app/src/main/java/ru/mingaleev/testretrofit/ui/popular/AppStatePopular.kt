package ru.mingaleev.testrv.ui.popular

import ru.mingaleev.testretrofit.data.dto.CurrenciesDTO

sealed class AppStatePopular {
    data class SuccessListExchange(val exchangeListData: CurrenciesDTO) : AppStatePopular()
    data class Error(val error: Throwable) : AppStatePopular()
}