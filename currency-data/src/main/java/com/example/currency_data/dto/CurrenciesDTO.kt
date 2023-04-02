package com.example.currency_data.dto

import com.example.currency_data_api.entity.CurrencyApi
import com.google.gson.annotations.SerializedName

data class CurrenciesDTO(
    val base: String,
    val date: String,
    @SerializedName("rates")
    val currencies: Map<String, Double>,
    val success: Boolean,
    val timestamp: Int
)

fun CurrenciesDTO.toCurrenciesList() : List<CurrencyApi> = currencies.toList().map{ CurrencyApi(it.first, it.second) }