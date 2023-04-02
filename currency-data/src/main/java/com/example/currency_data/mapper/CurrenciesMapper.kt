package com.example.currency_data.mapper

import com.example.currency_data.dto.CurrenciesDTO
import com.example.currency_data.room.CurrencyRoom
import com.example.currency_data_api.entity.CurrencyApi
import javax.inject.Inject

class CurrenciesMapper @Inject constructor() {

    fun mapCurrenciesFromDTO (from: CurrenciesDTO) : List<CurrencyApi> {
         return from.currencies.toList().map{ CurrencyApi(it.first, it.second) }
    }

    fun mapCurrenciesFromRoom (from: List<CurrencyRoom>): List<String> {
        return  from.map { it.name }
    }

    fun mapCurrencyToRoom (from: String): CurrencyRoom {
        return  CurrencyRoom(from)
    }

}