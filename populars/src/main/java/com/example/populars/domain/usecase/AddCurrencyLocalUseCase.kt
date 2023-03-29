package com.example.populars.domain.usecase

import com.example.currency_data_api.entity.Currency

interface AddCurrencyLocalUseCase {
    suspend operator fun invoke(currency: Currency)
}