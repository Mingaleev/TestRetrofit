package ru.mingaleev.testretrofit.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mingaleev.testretrofit.domain.AddCurrencyLocalUseCase
import ru.mingaleev.testretrofit.domain.GetCurrenciesListRemoteUseCase
import ru.mingaleev.testretrofit.domain.entity.Currency

class PopularViewModel(
    private val getCurrencyListUseCase: GetCurrenciesListRemoteUseCase = GetCurrenciesListRemoteUseCase(),
    private val addCurrencyToDBUseCase: AddCurrencyLocalUseCase = AddCurrencyLocalUseCase()
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