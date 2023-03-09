package ru.mingaleev.testretrofit.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mingaleev.testretrofit.data.RepositoryLocal
import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.dto.CurrenciesDTO
import ru.mingaleev.testretrofit.domain.entity.Currency
import javax.inject.Inject

class GetCurrenciesListLocalUseCase @Inject constructor(
    private val repositoryRemote: RepositoryRemote,
    private val repositoryLocal: RepositoryLocal,
) {
    suspend operator fun invoke(base: String): List<Currency> =
        withContext(Dispatchers.IO) {
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
