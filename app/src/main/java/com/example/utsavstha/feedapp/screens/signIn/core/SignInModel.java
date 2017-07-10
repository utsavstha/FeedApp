package com.example.utsavstha.feedapp.screens.signIn.core;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

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
import timber.log.Timber;

/**
 * Created by utsavstha on 7/9/17.
 */

public class SignInModel {

    private FirebaseAuth mAuth;

    private FirebaseUser mUser;
    private SignInActivity mSignInActivity;

    public SignInModel(SignInActivity signInActivity){
        this.mAuth = FirebaseAuth.getInstance();
        mSignInActivity = signInActivity;
    }

    Observable<Boolean> isNetworkAvailable(){
        return NetworkUtils.isNetworkAvailableObservable(mSignInActivity);
    }

    public void gotoSignUpActivity(){
        mSignInActivity.gotoSignUpActivity();
    }

    public void gotoFeedsListActivity(){
        mSignInActivity.gotoFeedListActivity();
    }

    public void loginFireBase(String email, String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(mSignInActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Timber.d("signInWithEmail:success");
                            mUser = mAuth.getCurrentUser();
                            gotoFeedsListActivity();
                        } else {
                            Toast.makeText(mSignInActivity, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

   /* public void registerToFireBase(final String name, final String email, final String password, final String imagePath){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(mSignInActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Timber.d("createUserWithEmail:success");
                            mUser = mAuth.getCurrentUser();

                            updateUser(name, imagePath);

                        } else {
                            // If sign in fails, display a message to the user.
                            Timber.w("createUserWithEmail:failure", task.getException());
                            Toast.makeText(mSignInActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }

    private void updateUser(String name, String imagePath) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(Uri.parse(imagePath))
                .build();
        mUser.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    String url = mUser.getPhotoUrl().toString();
                }
            }
        }) ;
    }*/

}
