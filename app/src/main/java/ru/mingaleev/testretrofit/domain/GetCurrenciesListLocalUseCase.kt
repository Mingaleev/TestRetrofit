package ru.mingaleev.testretrofit.domain

interface GetCurrenciesListLocalUseCase {
    suspend operator fun invoke(): List<String>
}