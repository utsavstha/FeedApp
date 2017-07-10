package com.example.utsavstha.feedapp.screens.signIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.utsavstha.feedapp.application.AppController;
import com.example.utsavstha.feedapp.screens.feeds.FeedsActivity;
import com.example.utsavstha.feedapp.screens.signIn.core.SignInPresenter;
import com.example.utsavstha.feedapp.screens.signIn.core.SignInView;
import com.example.utsavstha.feedapp.screens.signIn.dagger.DaggerSignInComponent;
import com.example.utsavstha.feedapp.screens.signIn.dagger.SignInContextModule;
import com.example.utsavstha.feedapp.screens.signUp.SignUpActivity;

import javax.inject.Inject;

public class SignInActivity extends AppCompatActivity {

    @Inject
    SignInView mSignInView;
    @Inject
    SignInPresenter mSignInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerSignInComponent.builder().appComponent(AppController.getAppComponent()).
                signInContextModule(new SignInContextModule(this))
                .build().inject(this);

        setContentView(mSignInView.constructView());
        mSignInPresenter.onCreate();
    }
    public void gotoSignUpActivity(){
        startActivity(new Intent(SignInActivity.this, SignUpActivity.class));

    }
    public void gotoFeedListActivity(){
        startActivity(new Intent(SignInActivity.this, FeedsActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSignInPresenter.onDestroy();
    }
}
