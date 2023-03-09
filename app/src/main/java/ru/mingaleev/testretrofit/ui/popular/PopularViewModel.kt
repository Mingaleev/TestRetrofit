package ru.mingaleev.testretrofit.ui.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mingaleev.testretrofit.domain.AddCurrencyLocalUseCase
import ru.mingaleev.testretrofit.domain.GetCurrenciesListRemoteUseCase
import ru.mingaleev.testretrofit.domain.entity.Currency
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val getCurrencyListUseCase: GetCurrenciesListRemoteUseCase,
    private val addCurrencyToDBUseCase: AddCurrencyLocalUseCase
) : ViewModel() {

    private val _ratesList: MutableLiveData<AppStatePopular> = MutableLiveData<AppStatePopular>()
    var ratesList: LiveData<AppStatePopular> = _ratesList

    init {
        getCurrencyList()
    }

    fun getCurrencyList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _ratesList.postValue(AppStatePopular.SuccessListExchange(getCurrencyListUseCase.invoke("USD")))
            } catch (e: Exception) {
                e.message?.let { Log.d("MINGA", it) }
                _ratesList.postValue(AppStatePopular.Error(e))
            }
        }
    }

    fun addToDB (currency: Currency) {
        viewModelScope.launch(Dispatchers.IO) {
            addCurrencyToDBUseCase.invoke(currency)
        }
    }
}