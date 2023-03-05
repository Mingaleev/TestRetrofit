package ru.mingaleev.testretrofit.ui.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.retrofit.RepositoryRemoteImp

class PopularViewModel(
    private val ratesList: MutableLiveData<AppStatePopular> = MutableLiveData<AppStatePopular>(),
    private val repositoryMA: RepositoryRemote = RepositoryRemoteImp()
) : ViewModel() {

    init {
        getCurrencyList()
    }

    fun getLiveData(): MutableLiveData<AppStatePopular> {
        return ratesList
    }

    fun getCurrencyList() {
        viewModelScope.launch {
            try {
                ratesList.postValue(AppStatePopular.SuccessListExchange(repositoryMA.getExchange("USD")))
            } catch (e: Exception) {
                ratesList.postValue(AppStatePopular.Error(e))
            }
        }
    }
}