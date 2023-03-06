package ru.mingaleev.testretrofit.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.retrofit.RepositoryRemoteImp
import ru.mingaleev.testretrofit.domain.GetCurrenciesListUseCase

class PopularViewModel(
    private val useCase: GetCurrenciesListUseCase = GetCurrenciesListUseCase()
) : ViewModel() {

    private val _ratesList: MutableLiveData<AppStatePopular> = MutableLiveData<AppStatePopular>()
    var ratesList: LiveData<AppStatePopular> = _ratesList

    init {
        getCurrencyList()
    }

    fun getCurrencyList() {
        viewModelScope.launch() {
            try {
                _ratesList.postValue(AppStatePopular.SuccessListExchange(useCase.invoke()))
            } catch (e: Exception) {
                _ratesList.postValue(AppStatePopular.Error(e))
            }
        }
    }
}