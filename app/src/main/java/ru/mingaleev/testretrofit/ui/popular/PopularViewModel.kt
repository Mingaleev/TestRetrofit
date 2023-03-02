package ru.mingaleev.testrv.ui.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.dto.Rate
import ru.mingaleev.testretrofit.data.retrofit.RepositoryRemoteImp

class PopularViewModel(
) : ViewModel() {

    private val repositoryMA: RepositoryRemote = RepositoryRemoteImp()

    val ratesList: MutableLiveData<
            List<Rate>> by lazy {
        MutableLiveData <
                List<Rate>>()
    }

    fun getCurrencyList() {
        viewModelScope.launch {
            ratesList.postValue(repositoryMA.getExchange("USD"))
        }
    }
}