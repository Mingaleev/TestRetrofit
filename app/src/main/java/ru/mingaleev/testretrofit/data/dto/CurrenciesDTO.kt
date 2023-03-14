package ru.mingaleev.testretrofit.data.dto


import com.google.gson.annotations.SerializedName
import ru.mingaleev.testretrofit.domain.entity.Currency

data class CurrenciesDTO(
    val base: String,
    val date: String,
    @SerializedName("rates")
    val currencies: Map<String, Double>,
    val success: Boolean,
    val timestamp: Int
)

fun CurrenciesDTO.toCurrenciesList() : List<Currency> = currencies.toList().map{ Currency(it.first, it.second) }