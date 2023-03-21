package ru.mingaleev.testretrofit.domain.interactor

import ru.mingaleev.testretrofit.data.RepositoryLocal
import ru.mingaleev.testretrofit.domain.GetCurrenciesListLocalUseCase
import javax.inject.Inject

class GetCurrenciesListLocalUseCaseImp @Inject constructor(private val repositoryLocal: RepositoryLocal) :
    GetCurrenciesListLocalUseCase {
    override suspend operator fun invoke(): List<String> {
        return repositoryLocal.get().map { (it.name) }
    }
}


