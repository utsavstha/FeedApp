package com.example.utsavstha.feedapp.screens.signIn.dagger;

import com.example.utsavstha.feedapp.application.builder.FireBaseModule;
import com.example.utsavstha.feedapp.screens.signIn.SignInActivity;
import com.example.utsavstha.feedapp.screens.signIn.core.SignInModel;
import com.example.utsavstha.feedapp.screens.signIn.core.SignInPresenter;
import com.example.utsavstha.feedapp.screens.signIn.core.SignInView;
import com.example.utsavstha.feedapp.utils.rxUtils.RxSchedulers;
import com.google.firebase.auth.FirebaseAuth;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by utsavstha on 7/9/17.
 */
@Module
public class SignInModule {

    @SignInScope
    @Provides
    SignInPresenter providePresenter(RxSchedulers schedulers, SignInModel signInModel, SignInView signInView){
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        return new SignInPresenter(signInModel, signInView, schedulers, compositeSubscription);
    }

    @SignInScope
    @Provides
    SignInModel provideSignInModel(SignInActivity signInActivity){
        return new SignInModel(signInActivity);
    }

    @SignInScope
    @Provides
    SignInView provideSignInView(SignInActivity signInActivity){
        return new SignInView(signInActivity);
    }
}
