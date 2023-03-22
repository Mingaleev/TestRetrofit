package com.example.core.data.dto


import com.example.core.entity.Currency
import com.google.gson.annotations.SerializedName

data class CurrenciesDTO(
    val base: String,
    val date: String,
    @SerializedName("rates")
    val currencies: Map<String, Double>,
    val success: Boolean,
    val timestamp: Int
)

fun CurrenciesDTO.toCurrenciesList() : List<Currency> = currencies.toList().map{ Currency(it.first, it.second) }