package ru.mingaleev.testretrofit.domain

import ru.mingaleev.testretrofit.domain.entity.Currency

interface GetCurrenciesListLocalUseCase {
    suspend operator fun invoke(): List<Currency>
}