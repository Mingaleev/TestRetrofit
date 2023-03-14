package ru.mingaleev.testretrofit

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.mingaleev.testretrofit.di.DaggerAppComponent

class MyApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this).build()
    }

}