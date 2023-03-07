package ru.mingaleev.testretrofit.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.dto.CurrenciesDTO
import ru.mingaleev.testretrofit.data.retrofit.RepositoryRemoteImp
import ru.mingaleev.testretrofit.domain.entity.Currency

class GetCurrenciesListUseCase(
    private val repository: RepositoryRemote = RepositoryRemoteImp(),
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(base: String): List<Currency> =
        withContext(defaultDispatcher) {
            val currencies: CurrenciesDTO? = null
            val listCurrency = currencies.let {
                repository.getExchange(base)
            }.currencies.toList().map { Currency(it.first, it.second) }
            listCurrency
        }
}