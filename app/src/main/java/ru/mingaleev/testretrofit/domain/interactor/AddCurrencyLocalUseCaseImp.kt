package ru.mingaleev.testretrofit.domain.interactor

import ru.mingaleev.testretrofit.data.RepositoryLocal
import ru.mingaleev.testretrofit.data.room.CurrencyRoom
import ru.mingaleev.testretrofit.domain.AddCurrencyLocalUseCase
import ru.mingaleev.testretrofit.domain.entity.Currency
import javax.inject.Inject

class AddCurrencyLocalUseCaseImp @Inject constructor(private val repositoryLocal: RepositoryLocal) :
    AddCurrencyLocalUseCase {
    override suspend fun invoke(currency: Currency) {
        repositoryLocal.insert(CurrencyRoom(currency.name))
    }
}