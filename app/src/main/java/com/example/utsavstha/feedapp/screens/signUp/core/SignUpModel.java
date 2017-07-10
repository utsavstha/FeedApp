package com.example.utsavstha.feedapp.screens.signUp.core;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.utsavstha.feedapp.screens.signUp.SignUpActivity;
import com.example.utsavstha.feedapp.utils.NetworkUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import rx.Observable;
import timber.log.Timber;

/**
 * Created by utsavstha on 7/9/17.
 */

public class SignUpModel {

    private FirebaseAuth mAuth;
    private SignUpActivity mSignUpActivity;
    private FirebaseUser mUser;


    public SignUpModel(SignUpActivity signUpActivity) {
        this.mAuth = FirebaseAuth.getInstance();
        this.mSignUpActivity = signUpActivity;
    }

    Observable<Boolean> isNetworkAvailable(){
        return NetworkUtils.isNetworkAvailableObservable(mSignUpActivity);
    }

    public void gotoFeedListActivity(){
        mSignUpActivity.gotoFeedListActivity();
    }

     public void registerToFireBase(final String name, final String email,
                                    final String password, final String imagePath){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(mSignUpActivity, new OnCompleteListener<AuthResult>() {
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
                            Toast.makeText(mSignUpActivity, "Registration Failed",
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
                    gotoFeedListActivity();
                }
            }
        }) ;
    }

}
