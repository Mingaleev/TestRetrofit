package ru.mingaleev.testretrofit.domain.interactor

import ru.mingaleev.testretrofit.data.RepositoryLocal
import ru.mingaleev.testretrofit.domain.GetCurrenciesListLocalUseCase
import ru.mingaleev.testretrofit.domain.entity.Currency
import javax.inject.Inject

class GetCurrenciesListLocalUseCaseImp @Inject constructor(private val repositoryLocal: RepositoryLocal) :
    GetCurrenciesListLocalUseCase {
    override suspend operator fun invoke(): List<Currency> {
        return repositoryLocal.get().map { Currency(it.name, 0.0) }
    }
}


