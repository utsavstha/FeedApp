package com.example.utsavstha.feedapp.application.builder;

import com.example.utsavstha.feedapp.utils.rxUtils.RxSchedulers;

import dagger.Component;

/**
 * Created by utsavstha on 7/9/17.
 */
@AppScope
@Component(modules = {ApplicationContextModule.class, RxModules.class, FireBaseModule.class})
public interface AppComponent {
    RxSchedulers rxSchedulers();

}
