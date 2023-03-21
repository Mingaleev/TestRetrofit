package ru.mingaleev.testretrofit.di

import dagger.Binds
import dagger.Module
import ru.mingaleev.testretrofit.domain.AddCurrencyLocalUseCase
import ru.mingaleev.testretrofit.domain.GetCurrenciesListLocalUseCase
import ru.mingaleev.testretrofit.domain.GetCurrenciesListRemoteUseCase
import ru.mingaleev.testretrofit.domain.RemoveCurrencyLocalUseCase
import ru.mingaleev.testretrofit.domain.interactor.AddCurrencyLocalUseCaseImp
import ru.mingaleev.testretrofit.domain.interactor.GetCurrenciesListLocalUseCaseImp
import ru.mingaleev.testretrofit.domain.interactor.GetCurrenciesListRemoteUseCaseImp
import ru.mingaleev.testretrofit.domain.interactor.RemoveCurrencyLocalUseCaseImp

@Module
interface DomainModule {

    @Binds
    fun provideAddCurrencyLocalUseCase(useCase: AddCurrencyLocalUseCaseImp): AddCurrencyLocalUseCase

    @Binds
    fun provideGetCurrenciesListLocalUseCase(useCase: GetCurrenciesListLocalUseCaseImp): GetCurrenciesListLocalUseCase

    @Binds
    fun provideGetCurrenciesListRemoteUseCase(useCase: GetCurrenciesListRemoteUseCaseImp): GetCurrenciesListRemoteUseCase

    @Binds
    fun provideRemoveCurrencyLocalUseCase(useCase: RemoveCurrencyLocalUseCaseImp): RemoveCurrencyLocalUseCase
}