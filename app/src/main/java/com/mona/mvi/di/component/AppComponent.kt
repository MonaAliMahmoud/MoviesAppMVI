package com.mona.mvi.di.component

import android.app.Application
import com.mona.mvi.di.module.ActivityModule
import com.mona.mvi.di.module.AppModule
import com.mona.mvi.di.module.NetworkModule
import com.mona.mvi.AppInstance
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun baseUrl(url : String) : AppComponent.Builder

        fun build(): AppComponent
    }

    fun inject(appInstance: AppInstance)
}