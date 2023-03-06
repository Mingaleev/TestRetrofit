package ru.mingaleev.testrv.data.room

import androidx.room.*

@Dao
interface CurrencyDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currency: Currency)

    @Delete
    suspend fun delete(currency: Currency)

    @Query("SELECT * FROM currency")
    suspend fun get(): List<Currency>
}