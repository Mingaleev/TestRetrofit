package ru.mingaleev.testretrofit.domain

import ru.mingaleev.testretrofit.domain.entity.Currency

interface GetCurrenciesListRemoteUseCase {
    suspend operator fun invoke(rate: String): List<Currency>
}