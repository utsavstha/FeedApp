package com.example.utsavstha.feedapp.screens.signUp.dagger;

import com.example.utsavstha.feedapp.screens.signUp.SignUpActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by utsavstha on 7/10/17.
 */

@Module
public class SignUpContextModule {
    SignUpActivity mSignUpActivity;

    public SignUpContextModule(SignUpActivity signUpActivity){
        this.mSignUpActivity = signUpActivity;
    }

    @SignUpScope
    @Provides
    SignUpActivity providesSignInContext(){
        return mSignUpActivity;
    }
}
