package com.mona.mvi.di.module

import com.mona.mvi.di.builder.MainActivityFragmentBuilderModule
import com.mona.mvi.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityFragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity

}