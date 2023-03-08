package ru.mingaleev.testretrofit.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyRoom(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val spot: Double
)
