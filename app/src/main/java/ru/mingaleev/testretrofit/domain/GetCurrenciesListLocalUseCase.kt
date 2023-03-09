package ru.mingaleev.testretrofit.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mingaleev.testretrofit.data.RepositoryLocal
import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.dto.CurrenciesDTO
import ru.mingaleev.testretrofit.data.retrofit.RepositoryRemoteImp
import ru.mingaleev.testretrofit.data.room.CurrencyRoom
import ru.mingaleev.testretrofit.data.room.RepositoryLocalImp
import ru.mingaleev.testretrofit.domain.entity.Currency

class GetCurrenciesListLocalUseCase(
    private val repositoryRemote: RepositoryRemote = RepositoryRemoteImp(),
    private val repositoryLocal: RepositoryLocal = RepositoryLocalImp(),
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(base: String): List<Currency> =
        withContext(defaultDispatcher) {
            val listCurrencyLocal = repositoryLocal.get().map { Currency(it.name, it.spot) }
            try {
                val currencies: CurrenciesDTO? = null
                val listCurrencyRemote = currencies.let {
                    repositoryRemote.getExchange(base)
                }.currencies

                listCurrencyLocal.forEach {
                    it.rate = listCurrencyRemote[it.name]!!
                }
                listCurrencyLocal
            } catch (e: Exception) {
                listCurrencyLocal
            }
        }
}
