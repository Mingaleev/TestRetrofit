package ru.mingaleev.testretrofit.domain

interface RemoveCurrencyLocalUseCase {
    suspend operator fun invoke(nameCurrency: String)
}