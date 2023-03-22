package com.example.populars.domain.usecase

import com.example.core.entity.Currency

interface AddCurrencyLocalUseCase {
    suspend operator fun invoke(currency: Currency)
}