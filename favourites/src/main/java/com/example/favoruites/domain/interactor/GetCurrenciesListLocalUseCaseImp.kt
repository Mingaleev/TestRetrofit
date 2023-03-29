package com.example.favoruites.domain.interactor

import com.example.currency_data_api.RepositoryLocal
import com.example.favoruites.domain.GetCurrenciesListLocalUseCase
import javax.inject.Inject

class GetCurrenciesListLocalUseCaseImp @Inject constructor(private val repositoryLocal: RepositoryLocal) :
    GetCurrenciesListLocalUseCase {
    override suspend operator fun invoke(): List<String> {
        return repositoryLocal.get().map { (it.name) }
    }
}


