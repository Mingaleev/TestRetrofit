package ru.mingaleev.testretrofit.ui.favoruites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mingaleev.testretrofit.domain.GetCurrenciesListLocalUseCase
import ru.mingaleev.testretrofit.domain.RemoveCurrencyLocalUseCase

class FavouritesViewModel(
    private val getCurrenciesListUseCase: GetCurrenciesListLocalUseCase = GetCurrenciesListLocalUseCase(),
    private val removeCurrencyLocalUseCase: RemoveCurrencyLocalUseCase = RemoveCurrencyLocalUseCase(),
) : ViewModel() {

    private val _ratesList: MutableLiveData<AppStateFavourites> = MutableLiveData<AppStateFavourites>()
    var ratesList: LiveData<AppStateFavourites> = _ratesList

    init {
        getCurrencyList()
    }

    fun getCurrencyList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _ratesList.postValue(AppStateFavourites.SuccessListExchange(getCurrenciesListUseCase.invoke("USD")))
            } catch (e: Exception) {
                _ratesList.postValue(AppStateFavourites.Error(e))
            }
        }
    }

    fun removeInDB(nameCurrency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            removeCurrencyLocalUseCase.invoke(nameCurrency)
            getCurrencyList()
        }
    }
}