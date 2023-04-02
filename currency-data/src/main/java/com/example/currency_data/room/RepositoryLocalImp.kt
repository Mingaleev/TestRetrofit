package com.example.currency_data.room

import com.example.currency_data.mapper.CurrenciesMapper
import com.example.currency_data_api.RepositoryLocal
import javax.inject.Inject

class RepositoryLocalImp @Inject constructor(
    private val currencyDb: CurrencyDatabase,
    private val currenciesMapper: CurrenciesMapper
) : RepositoryLocal {
    override suspend fun insert(currency: String) {
        currencyDb.currencyDao().insert(currenciesMapper.mapCurrencyToRoom(currency))
    }

    override suspend fun delete(nameCurrency: String) {
        currencyDb.currencyDao().deleteByName(nameCurrency)
    }

    override suspend fun get(): List<String> {
        val currencyRoomList = currencyDb.currencyDao().get()
        return currenciesMapper.mapCurrenciesFromRoom(currencyRoomList)
    }
}