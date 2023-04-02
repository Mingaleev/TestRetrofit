package com.example.populars.domain.usecase.interactor

import com.example.currency_data_api.RepositoryRemote
import com.example.currency_data_api.entity.CurrencyApi
import com.example.populars.domain.usecase.GetCurrenciesListRemoteUseCase
import javax.inject.Inject


class GetCurrenciesListRemoteUseCaseImp @Inject constructor(
    private val repository: RepositoryRemote
): GetCurrenciesListRemoteUseCase {
    override suspend operator fun invoke(rate: String): List<CurrencyApi> {
        return repository.getExchange(rate)
    }
}