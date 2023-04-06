package com.example.favoruites.ui

import androidx.lifecycle.*
import com.example.currency_data_api.entity.CurrencyApi
import com.example.favoruites.domain.GetCurrenciesListLocalUseCase
import com.example.favoruites.domain.GetCurrenciesListRemoteUseCase
import com.example.favoruites.domain.RemoveCurrencyLocalUseCase
import com.example.favoruites.domain.SortedAlphabetCurrenciesListUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class FavouritesViewModel @Inject constructor(
    private val getCurrenciesListLocalUseCase: GetCurrenciesListLocalUseCase,
    private val getCurrenciesListRemoteUseCase: GetCurrenciesListRemoteUseCase,
    private val removeCurrencyLocalUseCase: RemoveCurrencyLocalUseCase,
//    private val sortedAlphabetCurrenciesListUseCase: SortedAlphabetCurrenciesListUseCase

) : ViewModel() {

    private val _ratesList: MutableLiveData<AppStateFavourites> = MutableLiveData<AppStateFavourites>()
    var ratesList: LiveData<AppStateFavourites> = _ratesList
    private var baseCurrency = "AED"

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _ratesList.postValue(AppStateFavourites.Error(exception))
    }

    init {
        getCurrencyList(baseCurrency)
    }

    fun getCurrencyList(base: String) {
        baseCurrency = base

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val listLocal = async { getCurrenciesListLocalUseCase.invoke() }
            val listRemote = async { getCurrenciesListRemoteUseCase.invoke(base) }
            val result = awaitAll(listLocal, listRemote)
            _ratesList.postValue(
                AppStateFavourites.SuccessListExchange(
                    filter(result[0] as List<String>, result[1] as List<CurrencyApi>),
                    listForSpinner(result[1] as List<CurrencyApi>)
                )
            )
        }
    }

//    fun sortedAlphabet () {
//        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
//            sortedAlphabetCurrenciesListUseCase.invoke()
//        }
//    }

    private fun filter(listLocal: List<String>, listRemote: List<CurrencyApi>): List<CurrencyApi> {
        val listRemoteMap: Map<String, Double> = listRemote.associate { Pair(it.name, it.rate) }.toMap()
        val result = mutableListOf<CurrencyApi>()
        listLocal.forEach {
            result.add(CurrencyApi(it, listRemoteMap[it]!!))
        }
        return result
    }

    private fun listForSpinner(list: List<CurrencyApi>): List<String> {
        return list.map { it.name }
    }

    fun removeInDB(nameCurrency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            removeCurrencyLocalUseCase.invoke(nameCurrency)
            getCurrencyList(baseCurrency)
        }
    }
}