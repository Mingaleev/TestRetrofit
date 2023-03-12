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
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularViewModel @Inject constructor(
    private val getCurrencyListUseCase: GetCurrenciesListRemoteUseCase,
    private val addCurrencyToDBUseCase: AddCurrencyLocalUseCase
) : ViewModel() {

    private val _ratesList: MutableLiveData<AppStatePopular> = MutableLiveData<AppStatePopular>()
    var ratesList: LiveData<AppStatePopular> = _ratesList

    init {
        getCurrencyList("AED")
    }

    fun getCurrencyList(base: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _ratesList.postValue(AppStatePopular.SuccessListExchange(getCurrencyListUseCase.invoke(base)))
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