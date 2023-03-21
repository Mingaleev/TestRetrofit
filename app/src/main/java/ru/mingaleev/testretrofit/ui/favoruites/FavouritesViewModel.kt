package ru.mingaleev.testretrofit.ui.favoruites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mingaleev.testretrofit.domain.GetCurrenciesListLocalUseCase
import ru.mingaleev.testretrofit.domain.GetCurrenciesListRemoteUseCase
import ru.mingaleev.testretrofit.domain.RemoveCurrencyLocalUseCase
import ru.mingaleev.testretrofit.domain.entity.Currency
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class FavouritesViewModel @Inject constructor(
    private val getCurrenciesListLocalUseCase: GetCurrenciesListLocalUseCase,
    private val getCurrenciesListRemoteUseCase: GetCurrenciesListRemoteUseCase,
    private val removeCurrencyLocalUseCase: RemoveCurrencyLocalUseCase
) : ViewModel() {

    private val _ratesList: MutableLiveData<AppStateFavourites> = MutableLiveData<AppStateFavourites>()
    var ratesList: LiveData<AppStateFavourites> = _ratesList
    private var baseCurrency = "AED"

    init {
        getCurrencyList(baseCurrency)
    }

    fun getCurrencyList(base: String) {
        baseCurrency = base

        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            _ratesList.postValue(AppStateFavourites.Error(exception))
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val listLocal = async { getCurrenciesListLocalUseCase.invoke() }
            val listRemote = async { getCurrenciesListRemoteUseCase.invoke(base) }
            val result = awaitAll(listLocal, listRemote)
            _ratesList.postValue(
                AppStateFavourites.SuccessListExchange(
                    filter(result[0] as List<String>, result[1] as List<Currency>),
                    listForSpinner(result[1] as List<Currency>)
                )
            )
        }
    }

    private fun filter(listLocal: List<String>, listRemote: List<Currency>): List<Currency> {
        val listRemoteMap: Map<String, Double> = listRemote.associate { Pair(it.name, it.rate) }.toMap()
        val result = mutableListOf<Currency>()
        listLocal.forEach {
            result.add(Currency(it, listRemoteMap[it]!!))
        }
        return result
    }

    private fun listForSpinner(list: List<Currency>): List<String> {
        return list.map { it.name }
    }

    fun removeInDB(nameCurrency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            removeCurrencyLocalUseCase.invoke(nameCurrency)
            getCurrencyList(baseCurrency)
        }
    }
}