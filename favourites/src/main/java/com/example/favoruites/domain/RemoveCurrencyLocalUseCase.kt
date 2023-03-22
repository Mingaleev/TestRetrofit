package com.example.favoruites.domain

interface RemoveCurrencyLocalUseCase {
    suspend operator fun invoke(nameCurrency: String)
}