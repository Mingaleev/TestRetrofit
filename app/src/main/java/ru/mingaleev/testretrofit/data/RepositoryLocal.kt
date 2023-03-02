package ru.mingaleev.testretrofit.data

import androidx.room.Delete
import androidx.room.Insert
import ru.mingaleev.testrv.data.room.Currency

interface RepositoryLocal {

    @Insert
    suspend fun insert(currency: Currency)

    @Delete
    suspend fun delete(currency: Currency)

    @androidx.room.Query("SELECT * FROM currency")
    suspend fun get(): List<Currency>
}