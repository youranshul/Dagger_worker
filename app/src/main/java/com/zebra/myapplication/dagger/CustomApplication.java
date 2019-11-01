package com.zebra.myapplication.dagger;

import android.app.Application;

import androidx.work.Configuration;
import androidx.work.WorkManager;

import javax.inject.Inject;

import symbol.android.com.testapp.dagger.AppWorkerFactory;


/**
 * Created by AUpadhyay on 11/26/2018.
 */

public class CustomApplication extends Application {

    @Inject
    AppWorkerFactory factory;

    private AppComponent AppC;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        initWorkManager();
    }

    private void initWorkManager() {
        WorkManager.initialize(this, new Configuration.Builder().setWorkerFactory(factory).build());
    }

    private void initDagger() {
        AppC = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getComponent() {
        return AppC;
    }
}
