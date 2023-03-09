package ru.mingaleev.testretrofit.di

import dagger.Component
import ru.mingaleev.testretrofit.ui.popular.PopularFragment

@Component(modules = [ApiModule::class])
interface AppComponent {

    fun inject(fragment: PopularFragment)
}