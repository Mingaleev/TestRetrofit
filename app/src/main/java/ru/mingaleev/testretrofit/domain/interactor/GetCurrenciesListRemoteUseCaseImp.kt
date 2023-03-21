package ru.mingaleev.testretrofit.domain.interactor

import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.dto.toCurrenciesList
import ru.mingaleev.testretrofit.domain.GetCurrenciesListRemoteUseCase
import ru.mingaleev.testretrofit.domain.entity.Currency
import javax.inject.Inject


class GetCurrenciesListRemoteUseCaseImp @Inject constructor(
    private val repository: RepositoryRemote
): GetCurrenciesListRemoteUseCase {
    override suspend operator fun invoke(rate: String): List<Currency> {
        return repository.getExchange(rate).toCurrenciesList()
    }
}