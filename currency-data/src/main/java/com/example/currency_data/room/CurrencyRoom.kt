package com.example.currency_data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyRoom(
    @PrimaryKey val name: String
)
