package com.example.core.data

import androidx.room.Delete
import androidx.room.Insert
import com.example.core.data.room.CurrencyRoom

interface RepositoryLocal {

    @Insert
    suspend fun insert(currency: CurrencyRoom)

    @Delete
    suspend fun delete(nameCurrency: String)

    @androidx.room.Query("SELECT * FROM currency")
    suspend fun get(): List<CurrencyRoom>
}