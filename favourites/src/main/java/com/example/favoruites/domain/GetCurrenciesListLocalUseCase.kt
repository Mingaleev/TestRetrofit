package com.example.favoruites.domain

interface GetCurrenciesListLocalUseCase {
    suspend operator fun invoke(): List<String>
}