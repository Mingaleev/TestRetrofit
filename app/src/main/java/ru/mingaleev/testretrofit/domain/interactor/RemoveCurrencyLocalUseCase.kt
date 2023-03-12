package ru.mingaleev.testretrofit.domain.interactor

import ru.mingaleev.testretrofit.data.RepositoryLocal
import javax.inject.Inject

class RemoveCurrencyLocalUseCase @Inject constructor(
    private val repository: RepositoryLocal
) {
    suspend operator fun invoke(nameCurrency: String) {
        repository.delete(nameCurrency)
    }
}