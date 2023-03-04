package ru.mingaleev.testretrofit.ui.popular

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.dto.Currency
import ru.mingaleev.testretrofit.data.retrofit.RepositoryRemoteImp

class PopularViewModel: ViewModel() {

    private val repositoryMA: RepositoryRemote = RepositoryRemoteImp()

    val ratesList: MutableLiveData<List<Currency>> by lazy {
        MutableLiveData <List<Currency>>()
    }

    fun getCurrencyList() {
        viewModelScope.launch {
            try {
                ratesList.postValue(repositoryMA.getExchange("USD"))
            } catch (e: Exception) {
                e.message?.let { Log.d("Minga", it) }
            }
        }
    }
}