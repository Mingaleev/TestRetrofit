package com.example.populars.domain.usecase.interactor

import com.example.currency_data_api.RepositoryLocal
import com.example.currency_data_api.entity.CurrencyApi
import com.example.populars.domain.usecase.AddCurrencyLocalUseCase
import javax.inject.Inject

class AddCurrencyLocalUseCaseImp @Inject constructor(private val repositoryLocal: RepositoryLocal) :
    AddCurrencyLocalUseCase {
    override suspend fun invoke(currency: CurrencyApi) {
        repositoryLocal.insert(currency.name)
    }
}