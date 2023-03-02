package ru.mingaleev.testrv.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class Currency(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String?,
    val spot: String
)
