package ru.mingaleev.testretrofit.ui.favoruites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mingaleev.testretrofit.domain.entity.Currency
import ru.mingaleev.testretrofit.domain.interactor.GetCurrenciesListLocalUseCase
import ru.mingaleev.testretrofit.domain.interactor.GetCurrenciesListRemoteUseCase
import ru.mingaleev.testretrofit.domain.interactor.RemoveCurrencyLocalUseCase
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    private val getCurrenciesListLocalUseCase: GetCurrenciesListLocalUseCase,
    private val getCurrenciesListRemoteUseCase: GetCurrenciesListRemoteUseCase,
    private val removeCurrencyLocalUseCase: RemoveCurrencyLocalUseCase
) : ViewModel() {

    private val _ratesList: MutableLiveData<AppStateFavourites> = MutableLiveData<AppStateFavourites>()
    var ratesList: LiveData<AppStateFavourites> = _ratesList

    init {
        getCurrencyList()
    }

    fun getCurrencyList() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            _ratesList.postValue(AppStateFavourites.Error(exception))
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val listLocal = async { getCurrenciesListLocalUseCase.invoke() }
            val listRemote = async { getCurrenciesListRemoteUseCase.invoke("AED") }
            val result = awaitAll(listLocal, listRemote)
            _ratesList.postValue(
                AppStateFavourites.SuccessListExchange(
                    filter(result[0], result[1])
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
            getCurrencyList()
        }
    }
}