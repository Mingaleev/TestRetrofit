package ru.mingaleev.testretrofit.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.mingaleev.testretrofit.MyApp
import javax.inject.Singleton


@Component(
    modules = [
        ApiModule::class,
        RepositoryModule::class,
        DomainModule::class,
        FragmentBindingModule::class,
        AndroidSupportInjectionModule::class]
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
//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance context: Context): AppComponent
//    }
//
//    fun inject(fragment: PopularFragment)
//    fun inject(fragment: FavouritesFragment)
