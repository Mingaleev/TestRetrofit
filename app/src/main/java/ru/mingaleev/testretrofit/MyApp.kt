package ru.mingaleev.testretrofit

import android.app.Application
import androidx.room.Room
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mingaleev.testrv.data.room.CurrencyDatabase

class MyApp : Application() {

    companion object {
        lateinit var retrofit: Retrofit
        lateinit var currenciesDb: CurrencyDatabase
    }

    override fun onCreate() {
        super.onCreate()

        currenciesDb = Room.databaseBuilder(
            applicationContext,
            CurrencyDatabase::class.java,
            "CurrenciesDb"
        )
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.apilayer.com/exchangerates_data/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }
}