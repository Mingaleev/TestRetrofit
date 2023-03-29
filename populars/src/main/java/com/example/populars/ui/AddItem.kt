package com.example.populars.ui

import com.example.currency_data_api.entity.Currency

fun interface AddItem {
    fun add(currency: Currency)
}