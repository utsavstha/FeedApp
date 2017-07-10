package com.example.utsavstha.feedapp.screens.signUp.dagger;

import com.example.utsavstha.feedapp.application.builder.AppComponent;
import com.example.utsavstha.feedapp.screens.signUp.SignUpActivity;

import dagger.Component;

/**
 * Created by utsavstha on 7/9/17.
 */
@SignUpScope
@Component(modules = {SignUpContextModule.class, SignUpModule.class}, dependencies = {AppComponent.class})
public interface SignUpComponent {
    void inject(SignUpActivity signInActivity);
}

