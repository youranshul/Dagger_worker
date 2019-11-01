package com.zebra.myapplication.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Component
import dagger.Module
import dagger.Provides
import symbol.android.com.testapp.dagger.AppAssistedInjectModule
import symbol.android.com.testapp.dagger.AppWorkerFactory
import symbol.android.com.testapp.dagger.WorkerBindingModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, AppAssistedInjectModule::class, WorkerBindingModule::class])
interface AppComponent {

    fun con(): Context

    fun factory(): AppWorkerFactory
}

@Module
class AppModule(app: Application) {

    private val app: Context

    init {
        this.app = app
    }

    @Singleton
    @Provides
    fun provideCon(): Context {
        return app
    }

    @Singleton
    @Provides
    fun providePrefs(): SharedPreferences {
        return app.getSharedPreferences("test", Context.MODE_PRIVATE)
    }
}
