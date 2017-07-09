package com.example.utsavstha.feedapp.application.builder;

import com.example.utsavstha.feedapp.utils.rxUtils.ApplicationRxSchedulers;
import com.example.utsavstha.feedapp.utils.rxUtils.RxSchedulers;

import dagger.Module;
import dagger.Provides;

/**
 * Created by utsavstha on 7/9/17.
 */

@Module
public class RxModules {

    @AppScope
    @Provides
    RxSchedulers provideRxSchedulers(){
        return new ApplicationRxSchedulers();
    }
}
