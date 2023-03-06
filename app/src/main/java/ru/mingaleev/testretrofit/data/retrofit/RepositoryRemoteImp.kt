package ru.mingaleev.testretrofit.data.retrofit

import ru.mingaleev.testretrofit.MyApp
import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.dto.CurrenciesDTO

class RepositoryRemoteImp: RepositoryRemote {
    override suspend fun getExchange(base: String): CurrenciesDTO {
        return MyApp.retrofit
            .create(APIService::class.java)
            .getExchange(base)
    }
}