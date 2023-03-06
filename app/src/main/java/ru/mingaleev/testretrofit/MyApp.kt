package ru.mingaleev.testretrofit

import android.app.Application
import androidx.room.Room
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mingaleev.testrv.data.room.CurrencyDatabase

class MyApp : Application() {

    companion object {
        lateinit var retrofit: Retrofit
        lateinit var currenciesDb: CurrencyDatabase
        lateinit var clientHTTP: OkHttpClient
        lateinit var api_interceptor: Interceptor
    }

    override fun onCreate() {
        super.onCreate()

        currenciesDb = Room.databaseBuilder(
            applicationContext,
            CurrencyDatabase::class.java,
            "CurrenciesDb"
        )
            .build()

        api_interceptor = Interceptor {
            val originalRequest = it.request()
            val newHttpUrl = originalRequest.url.newBuilder()
                .addQueryParameter("apikey", "6mW1CWVu2AjDX796hZ24JIu67dyeTRSS")
                .build()
            val newRequest = originalRequest.newBuilder()
                .url(newHttpUrl)
                .build()
            it.proceed(newRequest)
        }

        clientHTTP = OkHttpClient().newBuilder()
            .addNetworkInterceptor(api_interceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.apilayer.com/exchangerates_data/")
            .client(clientHTTP)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }
}