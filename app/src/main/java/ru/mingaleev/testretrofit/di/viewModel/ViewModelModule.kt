package ru.mingaleev.testretrofit.di.viewModel

import androidx.lifecycle.ViewModel
import com.example.core.di.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.mingaleev.testretrofit.ui.favoruites.FavouritesViewModel
import ru.mingaleev.testretrofit.ui.popular.PopularViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    fun bindPopularViewModel(viewModel: PopularViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouritesViewModel::class)
    fun bindFavouritesViewModel(viewModel: FavouritesViewModel): ViewModel
}
