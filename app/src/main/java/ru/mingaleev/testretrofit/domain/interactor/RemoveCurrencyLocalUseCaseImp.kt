package ru.mingaleev.testretrofit.domain.interactor

import ru.mingaleev.testretrofit.data.RepositoryLocal
import ru.mingaleev.testretrofit.domain.RemoveCurrencyLocalUseCase
import javax.inject.Inject

class RemoveCurrencyLocalUseCaseImp @Inject constructor(private val repository: RepositoryLocal) :
    RemoveCurrencyLocalUseCase {
    override suspend operator fun invoke(nameCurrency: String) {
        repository.delete(nameCurrency)
    }
}