package com.example.utsavstha.feedapp.screens.signUp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.utsavstha.feedapp.R;
import com.example.utsavstha.feedapp.application.AppController;
import com.example.utsavstha.feedapp.screens.feeds.FeedsActivity;
import com.example.utsavstha.feedapp.screens.signIn.SignInActivity;
import com.example.utsavstha.feedapp.screens.signIn.dagger.SignInContextModule;
import com.example.utsavstha.feedapp.screens.signUp.core.SignUpPresenter;
import com.example.utsavstha.feedapp.screens.signUp.core.SignUpView;
import com.example.utsavstha.feedapp.screens.signUp.dagger.DaggerSignUpComponent;
import com.example.utsavstha.feedapp.screens.signUp.dagger.SignUpContextModule;
import com.example.utsavstha.feedapp.utils.firebaseUtils.FireBaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import javax.inject.Inject;

public class SignUpActivity extends AppCompatActivity {

    @Inject
    SignUpView mSignUpView;

    @Inject
    SignUpPresenter mSignUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSignUpComponent.builder().appComponent(AppController.getAppComponent()).
                signUpContextModule(new SignUpContextModule(this))
                .build().inject(this);
        setContentView(mSignUpView.constructView());

        FirebaseAuth.getInstance().signOut();
        mSignUpPresenter.onCreate();
    }

    public void gotoFeedListActivity(){
        startActivity(new Intent(SignUpActivity.this, FeedsActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSignUpPresenter.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        Uri uri = data.getData();
        try {
           // Bitmap picture = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

            mSignUpView.setImage(uri);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
