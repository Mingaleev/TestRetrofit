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
            val listForSpinner = async {
                val list = mutableListOf<String>()
                listRemote.await().forEach {
                    list.add(it.name)
                }
                return@async list
            }
            val result = awaitAll(listLocal, listRemote, listForSpinner)
            _ratesList.postValue(
                AppStateFavourites.SuccessListExchange(
                    filter(result[0] as List<Currency>, result[1] as List<Currency>), result[2] as List<String>
                )
            )
        }
    }

    private fun filter(listLocal: List<Currency>, listRemote: List<Currency>): List<Currency> {
        listLocal.forEach { local ->
            listRemote.forEach {
                if (it.name == local.name) local.rate = it.rate
            }
        }
        return listLocal
    }

    fun removeInDB(nameCurrency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            removeCurrencyLocalUseCase.invoke(nameCurrency)
            getCurrencyList(baseCurrency)
        }
    }
}