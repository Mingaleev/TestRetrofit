package ru.mingaleev.testretrofit.domain.interactor

import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.dto.toCurrenciesList
import ru.mingaleev.testretrofit.domain.entity.Currency
import javax.inject.Inject


class GetCurrenciesListRemoteUseCase @Inject constructor(
    private val repository: RepositoryRemote
) {
    suspend operator fun invoke(base: String): List<Currency> {
        return repository.getExchange(base).toCurrenciesList()
//        val currencies: CurrenciesDTO? = null
//        val listCurrency = currencies.let {
//            repository.getExchange(base)
//        }.currencies.toList().map { Currency(it.first, it.second) }
    }
}