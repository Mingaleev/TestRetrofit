package com.example.core.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currency: CurrencyRoom)

    @Query("DELETE FROM currency WHERE name = :name")
    fun deleteByName(name: String)

    @Query("SELECT * FROM currency")
    suspend fun get(): List<CurrencyRoom>
}