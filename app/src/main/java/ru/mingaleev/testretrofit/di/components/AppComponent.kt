package ru.mingaleev.testretrofit.di.components

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.mingaleev.testretrofit.MyApp
import ru.mingaleev.testretrofit.di.*
import ru.mingaleev.testretrofit.di.viewModel.ViewModelModule
import javax.inject.Singleton


@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        RepositoryModule::class,
        DomainModule::class,
        FragmentBindingModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelModule::class]
)
@Singleton
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun create(myApp: MyApp): Builder

        fun build(): AppComponent
    }
}
