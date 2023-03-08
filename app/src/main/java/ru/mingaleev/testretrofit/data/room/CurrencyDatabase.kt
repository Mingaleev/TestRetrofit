package ru.mingaleev.testrv.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mingaleev.testretrofit.data.room.CurrencyRoom

@Database(entities = [CurrencyRoom::class], version = 1)
abstract class CurrencyDatabase: RoomDatabase() {
    abstract fun currencyDao(): CurrencyDAO
}