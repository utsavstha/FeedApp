package com.example.utsavstha.feedapp.screens.feeds.dagger;

import com.example.utsavstha.feedapp.application.builder.AppComponent;
import com.example.utsavstha.feedapp.screens.feeds.FeedsActivity;

import dagger.Component;

/**
 * Created by utsavstha on 7/9/17.
 */

@FeedsScope
@Component(modules = {FeedsContextModule.class, FeedsModule.class}, dependencies = {AppComponent.class})
public interface FeedsComponent {
    void inject(FeedsActivity feedsActivity);
}
