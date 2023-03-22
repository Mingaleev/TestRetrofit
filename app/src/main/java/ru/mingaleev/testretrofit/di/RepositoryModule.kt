package ru.mingaleev.testretrofit.di

import com.example.core.data.RepositoryLocal
import com.example.core.data.RepositoryRemote
import com.example.core.data.retrofit.RepositoryRemoteImp
import com.example.core.data.room.RepositoryLocalImp
import dagger.Binds
import dagger.Module



@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepositoryLocal(repositoryLocal: RepositoryLocalImp): RepositoryLocal

    @Binds
    abstract fun provideRepositoryRemote(repositoryRemote: RepositoryRemoteImp): RepositoryRemote
}