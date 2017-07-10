package com.example.utsavstha.feedapp.screens.feeds.core;

import android.util.Log;

import com.example.utsavstha.feedapp.models.FeedDao;
import com.example.utsavstha.feedapp.screens.feeds.FeedsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by utsavstha on 7/9/17.
 */

public class FeedsModel {

    private FeedsActivity mFeedsActivity;
    private DatabaseReference mFireBaseDb;
    private FirebaseAuth mFireBaseAuth;
    private List<FeedDao> mFeeds;
    private Observable<List<FeedDao>> mFeedsList;


    public FeedsModel(FeedsActivity feedsActivity) {
        this.mFeedsActivity = feedsActivity;
        mFireBaseAuth = FirebaseAuth.getInstance();
        mFireBaseDb = FirebaseDatabase.getInstance().getReference();
    }

    public Observable<List<FeedDao>> getFeedsList() {

        return Observable.create(new Observable.OnSubscribe<List<FeedDao>>() {
            @Override
            public void call(final Subscriber<? super List<FeedDao>> subscriber) {

                mFireBaseDb.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        subscriber.onNext(getAllData(dataSnapshot));
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        subscriber.onNext(getAllData(dataSnapshot));
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        // taskDeletion(dataSnapshot);
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
    }

   /* private List<FeedDao> getDataFromFireBase() {


        mFeeds = new ArrayList<>();

        *//*mFireBaseDb.child("feeds").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    FeedDao feed = singleSnapshot.getValue(FeedDao.class);
                    mFeeds.add(feed);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*//*

        mFireBaseDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mFeedsList = Observable.just(getAllData(dataSnapshot));
                //  getAllData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                mFeedsList = Observable.just(getAllData(dataSnapshot));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // taskDeletion(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }*/

    private List<FeedDao> getAllData(DataSnapshot dataSnapshot) {
        mFeeds = new ArrayList<>();
        for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {

            int feedId = Integer.parseInt(singleSnapshot.child("feedId").getValue().toString());
            String feedImage = singleSnapshot.child("feedImage").getValue().toString();
            String status = singleSnapshot.child("status").getValue().toString();
            String userId = singleSnapshot.child("userId").getValue().toString();
            String userImage = singleSnapshot.child("userImage").getValue().toString();
            String userName = singleSnapshot.child("userName").getValue().toString();

            mFeeds.add(new FeedDao(feedId, feedImage, status, userId, userImage, userName));
        }
        return mFeeds;
    }


}
