package com.example.currency_data_api

import com.example.currency_data_api.entity.CurrencyApi


interface RepositoryRemote {
    suspend fun getExchange(base: String): List<CurrencyApi>
}