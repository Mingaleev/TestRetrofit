package ru.mingaleev.testretrofit.data.dto


import com.google.gson.annotations.SerializedName

data class CurrenciesDTO(
    val base: String,
    val date: String,
    @SerializedName("rates")
    val currencies: Map<String, Double>,
    val success: Boolean,
    val timestamp: Int
)