package ru.mingaleev.testretrofit.data.retrofit

import retrofit2.Retrofit
import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.dto.CurrenciesDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryRemoteImp @Inject constructor(private val retrofit: Retrofit): RepositoryRemote {
    override suspend fun getExchange(base: String): CurrenciesDTO {
        return retrofit
            .create(APIService::class.java)
            .getExchange(base)
    }
}