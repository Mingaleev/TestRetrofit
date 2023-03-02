package ru.mingaleev.testretrofit.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.dto.CurrenciesDTO
import ru.mingaleev.testretrofit.data.retrofit.RepositoryRemoteImp


class MainActivityViewModel() : ViewModel() {

    private val repositoryMA : RepositoryRemote = RepositoryRemoteImp()

    val ratesList: MutableLiveData< List<Pair<String, Double>>> by lazy {
        MutableLiveData< List<Pair<String, Double>>>()
    }

    fun getCurrencyList() {
        viewModelScope.launch {
            ratesList.postValue(repositoryMA.getExchange("USD"))
        }
    }
}