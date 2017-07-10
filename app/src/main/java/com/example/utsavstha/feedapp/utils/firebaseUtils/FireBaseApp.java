package com.example.utsavstha.feedapp.utils.firebaseUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by utsavstha on 7/10/17.
 */

public interface FireBaseApp {
    FirebaseAuth providesFireBaseAuth();

   // FirebaseUser providesFireBaseUser(FirebaseAuth firebaseAuth);

    FirebaseDatabase providesFireBaseDatabase();
}
