package com.example.core.data.room

import com.example.core.data.RepositoryLocal
import javax.inject.Inject

class RepositoryLocalImp @Inject constructor(private val currencyDb: CurrencyDatabase) : RepositoryLocal {
    override suspend fun insert(currency: CurrencyRoom) {
        currencyDb.currencyDao().insert(currency)
    }

    override suspend fun delete(nameCurrency: String) {
        currencyDb.currencyDao().deleteByName(nameCurrency)
    }

    override suspend fun get(): List<CurrencyRoom> {
        return currencyDb.currencyDao().get()
    }
}