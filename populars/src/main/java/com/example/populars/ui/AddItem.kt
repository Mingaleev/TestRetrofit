package com.example.populars.ui

import com.example.currency_data_api.entity.CurrencyApi

fun interface AddItem {
    fun add(currency: CurrencyApi)
}