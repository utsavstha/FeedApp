package com.example.utsavstha.feedapp.application;

import android.app.Application;

import com.example.utsavstha.feedapp.application.builder.AppComponent;
import com.example.utsavstha.feedapp.application.builder.ApplicationContextModule;
import com.example.utsavstha.feedapp.application.builder.DaggerAppComponent;

import timber.log.BuildConfig;
import timber.log.Timber;

/**
 * Created by utsavstha on 7/9/17.
 */

public class AppController extends Application{
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initialiseLogger();
        initAppComponent();

    }


    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .applicationContextModule(new ApplicationContextModule(this)).build();
    }


    private void initialiseLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    //TODO  decide what to log in release version
                }
            });
        }
    }

    public static AppComponent getNetComponent() {
        return appComponent;
    }
}
