package ru.mingaleev.testretrofit.ui.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.retrofit.RepositoryRemoteImp

class PopularViewModel: ViewModel() {

    private val repositoryMA: RepositoryRemote = RepositoryRemoteImp()

    val ratesList: MutableLiveData<AppStatePopular> by lazy {
        MutableLiveData<AppStatePopular>()
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