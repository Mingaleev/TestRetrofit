package ru.mingaleev.testretrofit.di.components

import com.example.currency_data.di.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.mingaleev.testretrofit.MyApp
import ru.mingaleev.testretrofit.di.*
import javax.inject.Singleton


@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        FragmentModule::class,
        AndroidSupportInjectionModule::class,
        RepositoryModule::class
    ]
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
