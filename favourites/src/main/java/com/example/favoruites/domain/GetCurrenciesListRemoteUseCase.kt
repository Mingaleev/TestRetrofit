package com.example.favoruites.domain

import com.example.core.entity.Currency

interface GetCurrenciesListRemoteUseCase {
    suspend operator fun invoke(rate: String): List<Currency>
}