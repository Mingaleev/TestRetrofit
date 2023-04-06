package ru.mingaleev.testretrofit.di

import com.example.favoruites.di.FavoritesModule
import com.example.favoruites.ui.FavouritesFragment
import com.example.populars.di.PopularModule
import com.example.populars.ui.PopularFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class
    ]
)
interface FragmentModule {

    @ContributesAndroidInjector (modules = [PopularModule::class])
    fun popularFragment(): PopularFragment

    @ContributesAndroidInjector (modules = [FavoritesModule::class])
    fun favoritesFragment(): FavouritesFragment
}