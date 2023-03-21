package ru.mingaleev.testretrofit.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.mingaleev.testretrofit.ui.favoruites.FavouritesFragment
import ru.mingaleev.testretrofit.ui.popular.PopularFragment

@Module(
    includes = [
        AndroidSupportInjectionModule::class
    ]
)
interface FragmentModule {

    @ContributesAndroidInjector
    fun popularFragment(): PopularFragment

    @ContributesAndroidInjector
    fun favoritesFragment(): FavouritesFragment
}