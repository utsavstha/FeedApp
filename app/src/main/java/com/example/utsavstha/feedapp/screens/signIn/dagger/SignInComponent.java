package com.example.utsavstha.feedapp.screens.signIn.dagger;

import com.example.utsavstha.feedapp.application.builder.AppComponent;
import com.example.utsavstha.feedapp.screens.signIn.SignInActivity;

import dagger.Component;

/**
 * Created by utsavstha on 7/9/17.
 */
@SignInScope
@Component(modules = {SignInContextModule.class, SignInModule.class}, dependencies = {AppComponent.class})
public interface SignInComponent {
    void inject(SignInActivity signInActivity);
}
