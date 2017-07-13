package com.example.utsavstha.feedapp.screens.signIn.core;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.utsavstha.feedapp.models.UserDao;
import com.example.utsavstha.feedapp.screens.signIn.SignInActivity;
import com.example.utsavstha.feedapp.utils.NetworkUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import timber.log.Timber;

/**
 * Created by utsavstha on 7/9/17.
 */

public class SignInModel {

    private FirebaseAuth mAuth;

    private FirebaseUser mUser;
    private Boolean mTask;
    private SignInActivity mSignInActivity;


    public SignInModel(SignInActivity signInActivity) {
        this.mAuth = FirebaseAuth.getInstance();
        mSignInActivity = signInActivity;
    }

    Observable<Boolean> isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailableObservable(mSignInActivity);
    }

    public void gotoSignUpActivity() {
        mSignInActivity.gotoSignUpActivity();
    }

    public void gotoFeedsListActivity() {
        mSignInActivity.gotoFeedListActivity();
    }

    public Observable<Boolean> loginFireBase(final String email, final String password) {

       return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(final Subscriber<? super Boolean> subscriber) {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(mSignInActivity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    mTask = task.isSuccessful();
                                    // Sign in success, update UI with the signed-in user's information
                                    Timber.d("signInWithEmail:success");
                                    mUser = mAuth.getCurrentUser();
                                    subscriber.onNext(true);

                                } else {
                                    Toast.makeText(mSignInActivity, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });


    }


}
