package com.mona.mvi.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mona.mvi.di.helper.ViewModelKey
import com.mona.mvi.ui.base.ViewModelFactory
import com.mona.mvi.ui.main.actorslist.ActorsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ActorsListViewModel::class)
    abstract fun bindActorsListViewModel(actorsListViewModel: ActorsListViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory
}