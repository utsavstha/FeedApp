package com.example.utsavstha.feedapp.application.builder;

import android.os.Bundle;

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
    private FirebaseUser mFireBaseUser;
    private DatabaseReference mFireBaseDb;

    @AppScope
    @Provides
    FirebaseAuth providesFireBaseAuth(){
        return FirebaseAuth.getInstance();
    }

    @AppScope
    @Provides
    FirebaseUser providesFireBaseUser(FirebaseAuth firebaseAuth){
        return firebaseAuth.getCurrentUser();
    }

    @AppScope
    @Provides
    FirebaseDatabase providesFireBaseDatabase(){
        return FirebaseDatabase.getInstance();
    }

}
