package ru.mingaleev.testretrofit.data

import androidx.room.Delete
import androidx.room.Insert
import ru.mingaleev.testretrofit.data.room.CurrencyRoom

interface RepositoryLocal {

    @Insert
    suspend fun insert(currency: CurrencyRoom)

    @Delete
    suspend fun delete(currency: CurrencyRoom)

    @androidx.room.Query("SELECT * FROM currency")
    suspend fun get(): List<CurrencyRoom>
}