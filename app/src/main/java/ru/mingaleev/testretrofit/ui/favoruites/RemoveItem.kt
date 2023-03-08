package ru.mingaleev.testretrofit.ui.favoruites

import ru.mingaleev.testretrofit.domain.entity.Currency

fun interface RemoveItem {
    fun delete(nameCurrency: String)
}