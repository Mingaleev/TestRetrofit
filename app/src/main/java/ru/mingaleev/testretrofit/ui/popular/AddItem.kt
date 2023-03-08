package ru.mingaleev.testretrofit.ui.popular

import ru.mingaleev.testretrofit.domain.entity.Currency

fun interface AddItem {
    fun add(currency: Currency)
}