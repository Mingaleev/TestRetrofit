package ru.mingaleev.testretrofit.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.mingaleev.testretrofit.ui.favoruites.FavouritesFragment
import ru.mingaleev.testretrofit.ui.popular.PopularFragment

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun popularFragment(): PopularFragment

    @ContributesAndroidInjector
    abstract fun favoritesFragment(): FavouritesFragment
}