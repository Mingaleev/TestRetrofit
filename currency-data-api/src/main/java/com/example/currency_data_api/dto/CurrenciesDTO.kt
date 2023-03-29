package com.example.currency_data_api.dto

import com.example.currency_data_api.entity.Currency
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