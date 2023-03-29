package com.example.favoruites.domain.interactor

import com.example.currency_data_api.RepositoryLocal
import com.example.favoruites.domain.RemoveCurrencyLocalUseCase
import javax.inject.Inject

class RemoveCurrencyLocalUseCaseImp @Inject constructor(private val repository: RepositoryLocal) :
    RemoveCurrencyLocalUseCase {
    override suspend operator fun invoke(nameCurrency: String) {
        repository.delete(nameCurrency)
    }
}