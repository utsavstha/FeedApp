package com.example.utsavstha.feedapp.application.builder;

import android.os.Bundle;

import com.example.utsavstha.feedapp.utils.firebaseUtils.ApplicationFireBase;
import com.example.utsavstha.feedapp.utils.firebaseUtils.FireBaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by utsavstha on 7/9/17.
 */

@Module
public class FireBaseModule {

    @AppScope
    @Provides
    FireBaseApp provideFireBaseApp(){
       return new ApplicationFireBase();
    }
}
