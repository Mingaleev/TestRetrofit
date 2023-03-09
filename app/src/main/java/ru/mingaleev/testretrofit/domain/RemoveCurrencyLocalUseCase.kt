package ru.mingaleev.testretrofit.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mingaleev.testretrofit.data.RepositoryLocal
import javax.inject.Inject

class RemoveCurrencyLocalUseCase @Inject constructor(
    private val repository: RepositoryLocal
) {
    suspend operator fun invoke(nameCurrency: String) =
        withContext(Dispatchers.IO) {
            repository.delete(nameCurrency)
        }
}