package com.mona.mvi.di.builder

import com.mona.mvi.ui.main.actorslist.ActorsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): ActorsListFragment
}