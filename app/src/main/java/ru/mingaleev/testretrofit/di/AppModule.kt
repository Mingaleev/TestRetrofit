package ru.mingaleev.testretrofit.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.viewModel.ViewModelFactory
import dagger.Binds
import dagger.Module
import ru.mingaleev.testretrofit.MyApp
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    @Singleton
    fun context(myApp: MyApp): Context

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}