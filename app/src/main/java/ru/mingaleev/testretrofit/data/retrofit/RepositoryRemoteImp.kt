package ru.mingaleev.testretrofit.data.retrofit

import ru.mingaleev.testretrofit.MyApp
import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.dto.Rate

class RepositoryRemoteImp: RepositoryRemote {
    override suspend fun getExchange(base: String): List<Rate> {
        return MyApp.retrofit
            .create(APIService::class.java)
            .getExchange(key = "6mW1CWVu2AjDX796hZ24JIu67dyeTRSS", base = base)
            .rates.toList().map { Rate(it.first, it.second) }
    }
}