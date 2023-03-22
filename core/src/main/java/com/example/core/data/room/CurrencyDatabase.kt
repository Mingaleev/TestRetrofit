package com.example.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyRoom::class], version = 1)
abstract class CurrencyDatabase: RoomDatabase() {
    abstract fun currencyDao(): CurrencyDAO
}