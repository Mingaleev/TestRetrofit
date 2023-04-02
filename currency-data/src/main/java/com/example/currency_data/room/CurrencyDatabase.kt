package com.example.currency_data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.currency_data_api.CurrencyRoom

@Database(entities = [CurrencyRoom::class], version = 1)
abstract class CurrencyDatabase: RoomDatabase() {
    abstract fun currencyDao(): CurrencyDAO
}