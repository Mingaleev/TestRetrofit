package ru.mingaleev.testrv.data.room

import androidx.room.*
import ru.mingaleev.testretrofit.data.room.CurrencyRoom

@Dao
interface CurrencyDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currency: CurrencyRoom)

    @Delete
    suspend fun delete(currency: CurrencyRoom)

    @Query("SELECT * FROM currency")
    suspend fun get(): List<CurrencyRoom>
}