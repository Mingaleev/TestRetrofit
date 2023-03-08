package ru.mingaleev.testretrofit.data.room

import ru.mingaleev.testretrofit.MyApp
import ru.mingaleev.testretrofit.data.RepositoryLocal

class RepositoryLocalImp: RepositoryLocal {
    override suspend fun insert(currency: CurrencyRoom) {
        MyApp.currenciesDb.currencyDao().insert(currency)
    }

    override suspend fun delete(currency: CurrencyRoom) {
        MyApp.currenciesDb.currencyDao().delete(currency)
    }

    override suspend fun get(): List<CurrencyRoom> {
        return MyApp.currenciesDb.currencyDao().get()
    }
}