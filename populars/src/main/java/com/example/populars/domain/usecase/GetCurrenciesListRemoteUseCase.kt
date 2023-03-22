package com.example.populars.domain.usecase

import com.example.core.entity.Currency

interface GetCurrenciesListRemoteUseCase {
    suspend operator fun invoke(rate: String): List<Currency>
}