package com.example.utsavstha.feedapp.screens.signIn.dagger;

import com.example.utsavstha.feedapp.screens.signIn.SignInActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by utsavstha on 7/10/17.
 */

@Module
public class SignInContextModule {
    SignInActivity mSignInActivity;

    public SignInContextModule(SignInActivity signInActivity){
        this.mSignInActivity = signInActivity;
    }

    @SignInScope
    @Provides
    SignInActivity providesSignInContext(){
        return mSignInActivity;
    }
}
