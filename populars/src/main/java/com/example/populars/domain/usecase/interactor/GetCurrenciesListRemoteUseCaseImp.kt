package com.example.populars.domain.usecase.interactor

import com.example.core.data.RepositoryRemote
import com.example.core.data.dto.toCurrenciesList
import com.example.core.entity.Currency
import com.example.populars.domain.usecase.GetCurrenciesListRemoteUseCase
import javax.inject.Inject


class GetCurrenciesListRemoteUseCaseImp @Inject constructor(
    private val repository: RepositoryRemote
): GetCurrenciesListRemoteUseCase {
    override suspend operator fun invoke(rate: String): List<Currency> {
        return repository.getExchange(rate).toCurrenciesList()
    }
}