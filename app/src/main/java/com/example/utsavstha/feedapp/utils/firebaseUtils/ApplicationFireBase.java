package com.example.utsavstha.feedapp.utils.firebaseUtils;

import com.example.utsavstha.feedapp.application.builder.AppScope;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Provides;

/**
 * Created by utsavstha on 7/10/17.
 */

public class ApplicationFireBase implements FireBaseApp{
/*    FirebaseAuth mFireBaseAuth;
    FirebaseUser mFireBaseUser;
    DatabaseReference mFireBaseDb;*/

    @Override
    public FirebaseAuth providesFireBaseAuth(){
        return FirebaseAuth.getInstance();
    }

   /* @Override
    public FirebaseUser providesFireBaseUser(FirebaseAuth firebaseAuth){
        return firebaseAuth.getCurrentUser();
    }*/

    @Override
    public FirebaseDatabase providesFireBaseDatabase(){
        return FirebaseDatabase.getInstance();
    }

}
