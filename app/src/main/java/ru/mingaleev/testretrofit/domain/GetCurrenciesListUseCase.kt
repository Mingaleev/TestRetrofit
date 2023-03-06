package ru.mingaleev.testretrofit.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.dto.CurrenciesDTO
import ru.mingaleev.testretrofit.data.retrofit.RepositoryRemoteImp

class GetCurrenciesListUseCase(
    private val repository: RepositoryRemote = RepositoryRemoteImp(),
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): List<Currency> =
        withContext(defaultDispatcher) {
            val currencies: CurrenciesDTO? = null
            val listCurrency = currencies.let {
                repository.getExchange("USD")
            }.currencies.toList().map { Currency(it.first, it.second) }
            listCurrency
        }

}