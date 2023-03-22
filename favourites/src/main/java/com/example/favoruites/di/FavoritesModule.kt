package com.example.favoruites.di

import androidx.lifecycle.ViewModel
import com.example.core.di.viewModel.ViewModelKey
import com.example.favoruites.domain.GetCurrenciesListLocalUseCase
import com.example.favoruites.domain.GetCurrenciesListRemoteUseCase
import com.example.favoruites.domain.RemoveCurrencyLocalUseCase
import com.example.favoruites.domain.interactor.GetCurrenciesListLocalUseCaseImp
import com.example.favoruites.domain.interactor.GetCurrenciesListRemoteUseCaseImp
import com.example.favoruites.domain.interactor.RemoveCurrencyLocalUseCaseImp
import com.example.favoruites.ui.FavouritesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FavoritesModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavouritesViewModel::class)
    fun bindFavouritesViewModel(viewModel: FavouritesViewModel): ViewModel

    @Binds
    fun provideAddCurrencyLocalUseCase(useCase: GetCurrenciesListLocalUseCaseImp): GetCurrenciesListLocalUseCase

    @Binds
    fun provideGetCurrenciesListRemoteUseCase(useCase: GetCurrenciesListRemoteUseCaseImp): GetCurrenciesListRemoteUseCase

    @Binds
    fun provideRemoveCurrencyLocalUseCase(useCase: RemoveCurrencyLocalUseCaseImp): RemoveCurrencyLocalUseCase
}