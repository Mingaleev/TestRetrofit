package com.example.populars.domain.usecase.interactor

import com.example.core.data.RepositoryLocal
import com.example.core.data.room.CurrencyRoom
import com.example.core.entity.Currency
import com.example.populars.domain.usecase.AddCurrencyLocalUseCase
import javax.inject.Inject

class AddCurrencyLocalUseCaseImp @Inject constructor(private val repositoryLocal: RepositoryLocal) :
    AddCurrencyLocalUseCase {
    override suspend fun invoke(currency: Currency) {
        repositoryLocal.insert(CurrencyRoom(currency.name))
    }
}