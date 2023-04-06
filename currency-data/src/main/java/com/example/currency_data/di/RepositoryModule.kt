package com.example.currency_data.di

import com.example.currency_data.retrofit.RepositoryRemoteImp
import com.example.currency_data.room.RepositoryLocalImp
import com.example.currency_data_api.RepositoryLocal
import com.example.currency_data_api.RepositoryRemote
import dagger.Binds
import dagger.Module



@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepositoryLocal(repositoryLocal: RepositoryLocalImp): RepositoryLocal

    @Binds
    abstract fun provideRepositoryRemote(repositoryRemote: RepositoryRemoteImp): RepositoryRemote
}