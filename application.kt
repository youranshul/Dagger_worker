package com.zebra.myapplication.dagger

import android.app.Application
import android.util.Log
import androidx.work.Configuration

class CustomApplication : Application(), Configuration.Provider {

    lateinit var factory: AppWorkerFactory

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        factory = component.factory()
        super.onCreate()
        Log.i("Ansh", "onCreate")
    }


    override fun getWorkManagerConfiguration(): Configuration {
        Log.i("Ansh", "getWorkManagerConfiguration" + factory!!)
        return Configuration.Builder().setWorkerFactory(factory!!).build()
    }
}