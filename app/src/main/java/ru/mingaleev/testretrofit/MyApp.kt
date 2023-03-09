package ru.mingaleev.testretrofit

import android.app.Application
import ru.mingaleev.testretrofit.di.AppComponent
import ru.mingaleev.testretrofit.di.DaggerAppComponent

class MyApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}