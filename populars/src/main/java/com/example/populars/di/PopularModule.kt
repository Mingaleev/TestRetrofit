package com.example.populars.di

import androidx.lifecycle.ViewModel
import com.example.core.di.viewModel.ViewModelKey
import com.example.populars.domain.usecase.AddCurrencyLocalUseCase
import com.example.populars.domain.usecase.GetCurrenciesListRemoteUseCase
import com.example.populars.domain.usecase.interactor.AddCurrencyLocalUseCaseImp
import com.example.populars.domain.usecase.interactor.GetCurrenciesListRemoteUseCaseImp
import com.example.populars.ui.PopularViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PopularModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    fun bindPopularViewModel(viewModel: PopularViewModel): ViewModel

    @Binds
    fun provideGetCurrenciesListRemoteUseCase(useCase: GetCurrenciesListRemoteUseCaseImp): GetCurrenciesListRemoteUseCase

    @Binds
    fun provideAddCurrencyLocalUseCase(useCase: AddCurrencyLocalUseCaseImp): AddCurrencyLocalUseCase
}