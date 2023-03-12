package ru.mingaleev.testretrofit.domain.interactor

import ru.mingaleev.testretrofit.data.RepositoryLocal
import ru.mingaleev.testretrofit.domain.entity.Currency
import javax.inject.Inject

class GetCurrenciesListLocalUseCase @Inject constructor(
    private val repositoryLocal: RepositoryLocal,
) {
    suspend operator fun invoke(): List<Currency> {
            return repositoryLocal.get().map { Currency(it.name, 0.0) }
        }
}


