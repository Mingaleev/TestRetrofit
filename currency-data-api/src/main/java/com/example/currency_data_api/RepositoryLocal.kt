package com.example.currency_data_api

import androidx.room.Delete
import androidx.room.Insert

interface RepositoryLocal {

    @Insert
    suspend fun insert(currency: CurrencyRoom)

    @Delete
    suspend fun delete(nameCurrency: String)

    @androidx.room.Query("SELECT * FROM currency")
    suspend fun get(): List<CurrencyRoom>
}