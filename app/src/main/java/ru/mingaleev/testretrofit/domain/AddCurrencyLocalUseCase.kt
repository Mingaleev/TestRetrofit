package ru.mingaleev.testretrofit.domain

import ru.mingaleev.testretrofit.domain.entity.Currency

interface AddCurrencyLocalUseCase {
    suspend operator fun invoke(currency: Currency)
}