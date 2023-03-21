package ru.mingaleev.testretrofit.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
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
        getCurrencyList("AED")
    }

    fun getCurrencyList(base: String) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            _ratesList.postValue(AppStatePopular.Error(exception))
        }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _ratesList.postValue(AppStatePopular.SuccessListExchange(getCurrencyListUseCase.invoke(base)))
        }
    }

    fun addToDB (currency: Currency) {
        viewModelScope.launch(Dispatchers.IO) {
            addCurrencyToDBUseCase.invoke(currency)
        }
    }
}