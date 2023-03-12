package ru.mingaleev.testretrofit.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.mingaleev.testretrofit.ui.favoruites.FavouritesFragment
import ru.mingaleev.testretrofit.ui.popular.PopularFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RepositoryModule::class, DomainModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: PopularFragment)
    fun inject(fragment: FavouritesFragment)
}