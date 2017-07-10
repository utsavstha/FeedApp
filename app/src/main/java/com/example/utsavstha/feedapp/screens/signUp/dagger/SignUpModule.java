package com.example.utsavstha.feedapp.screens.signUp.dagger;

import com.example.utsavstha.feedapp.screens.signUp.SignUpActivity;
import com.example.utsavstha.feedapp.screens.signUp.core.SignUpModel;
import com.example.utsavstha.feedapp.screens.signUp.core.SignUpPresenter;
import com.example.utsavstha.feedapp.screens.signUp.core.SignUpView;
import com.example.utsavstha.feedapp.utils.rxUtils.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by utsavstha on 7/9/17.
 */

@Module
public class SignUpModule {
    @SignUpScope
    @Provides
    SignUpPresenter providePresenter(RxSchedulers schedulers, SignUpModel signUpModel, SignUpView signUpView){
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        return new SignUpPresenter(signUpModel, signUpView, schedulers, compositeSubscription);
    }

    @SignUpScope
    @Provides
    SignUpModel provideSignInModel(SignUpActivity signUpActivity){
        return new SignUpModel(signUpActivity);
    }

    @SignUpScope
    @Provides
    SignUpView provideSignInView(SignUpActivity signUpActivity){
        return new SignUpView(signUpActivity);
    }
}
