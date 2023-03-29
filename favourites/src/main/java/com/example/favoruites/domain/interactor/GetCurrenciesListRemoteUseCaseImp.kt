package com.example.favoruites.domain.interactor


import com.example.currency_data_api.RepositoryRemote
import com.example.currency_data_api.dto.toCurrenciesList
import com.example.currency_data_api.entity.Currency
import com.example.favoruites.domain.GetCurrenciesListRemoteUseCase
import javax.inject.Inject


class GetCurrenciesListRemoteUseCaseImp @Inject constructor(
    private val repository: RepositoryRemote
): GetCurrenciesListRemoteUseCase {
    override suspend operator fun invoke(rate: String): List<Currency> {
        return repository.getExchange(rate).toCurrenciesList()
    }
}