package com.zebra.myapplication.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, ActivityModule::class, AppAssistedInjectModule::class, WorkerBindingModule::class, AppSubcomponents::class])
interface AppComponent {

    fun plus(): ActivityComponent.Factory

    fun second():SecondActivityComponent.Factory

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

@Singleton
class ViData @Inject constructor(private val data: Data/*, private val niData: NiData*/) {
    fun setData(yes: String) {
        Log.i("Ansh","ViData Called $yes")
    }
}

@Module(
    subcomponents = [
        ActivityComponent::class,SecondActivityComponent::class
    ]
)
class AppSubcomponents
