package ru.mingaleev.testretrofit.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mingaleev.testretrofit.data.RepositoryLocal
import ru.mingaleev.testretrofit.data.room.CurrencyRoom
import ru.mingaleev.testretrofit.domain.entity.Currency
import javax.inject.Inject

class AddCurrencyLocalUseCase @Inject constructor(
    private val repository: RepositoryLocal
) {
    suspend operator fun invoke(currency: Currency) =
        withContext(Dispatchers.IO) {
            repository.insert(CurrencyRoom(currency.name, currency.rate))
        }
}