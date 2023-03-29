package com.example.populars.domain.usecase

import com.example.currency_data_api.entity.Currency


interface GetCurrenciesListRemoteUseCase {
    suspend operator fun invoke(rate: String): List<Currency>
}