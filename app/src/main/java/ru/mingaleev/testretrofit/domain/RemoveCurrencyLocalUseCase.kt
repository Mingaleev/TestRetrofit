package ru.mingaleev.testretrofit.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mingaleev.testretrofit.data.RepositoryLocal
import ru.mingaleev.testretrofit.data.room.CurrencyRoom
import ru.mingaleev.testretrofit.data.room.RepositoryLocalImp
import ru.mingaleev.testretrofit.domain.entity.Currency

class RemoveCurrencyLocalUseCase(
    private val repository: RepositoryLocal = RepositoryLocalImp(),
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(nameCurrency: String) =
        withContext(defaultDispatcher) {
            repository.delete(nameCurrency)
        }
}