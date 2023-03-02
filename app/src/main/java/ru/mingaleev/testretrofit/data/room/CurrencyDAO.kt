package ru.mingaleev.testrv.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CurrencyDAO {
    @Insert
    suspend fun insert(currency: Currency)

    @Delete
    suspend fun delete(currency: Currency)

    @Query("SELECT * FROM currency")
    suspend fun get(): List<Currency>
}