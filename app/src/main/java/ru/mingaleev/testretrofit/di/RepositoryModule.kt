package ru.mingaleev.testretrofit.di

import dagger.Binds
import dagger.Module
import ru.mingaleev.testretrofit.data.RepositoryLocal
import ru.mingaleev.testretrofit.data.RepositoryRemote
import ru.mingaleev.testretrofit.data.retrofit.RepositoryRemoteImp
import ru.mingaleev.testretrofit.data.room.RepositoryLocalImp

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepositoryLocal(repositoryLocal: RepositoryLocalImp): RepositoryLocal

    @Binds
    abstract fun provideRepositoryRemote(repositoryRemote: RepositoryRemoteImp): RepositoryRemote
}