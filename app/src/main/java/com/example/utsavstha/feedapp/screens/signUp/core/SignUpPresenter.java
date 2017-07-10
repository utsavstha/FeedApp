package com.example.utsavstha.feedapp.screens.signUp.core;

import android.widget.ImageView;

import com.example.utsavstha.feedapp.models.UserDao;
import com.example.utsavstha.feedapp.screens.signIn.core.SignInView;
import com.example.utsavstha.feedapp.utils.rxUtils.RxSchedulers;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by utsavstha on 7/9/17.
 */

public class SignUpPresenter {

    private SignUpModel mSignUpModel;
    private SignUpView mSignUpView;
    private RxSchedulers mRxSchedulers;
    private CompositeSubscription mSubscriptions;
    private String imageUri;

    public SignUpPresenter(SignUpModel signUpModel, SignUpView signUpView, RxSchedulers schedulers, CompositeSubscription compositeSubscription) {
        this.mSignUpModel = signUpModel;
        this.mSignUpView = signUpView;
        this.mRxSchedulers = schedulers;
        this.mSubscriptions = compositeSubscription;
    }

    public void onCreate(){
        mSubscriptions.add(register());
        mSubscriptions.add(loadImage());
    }

    private Subscription loadImage() {
        return mSignUpView.uploadClicks().subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                mSignUpView.showSelectMenu();
            }
        });
    }

    private Subscription register() {
        return mSignUpModel.isNetworkAvailable().doOnNext(new Action1<Boolean>() {
            @Override
            public void call(Boolean networkAvailable) {
                if(!networkAvailable){
                    Timber.d("No internet connection.");
                }
            }
        }).
                filter(new Func1<Boolean, Boolean>() {
                    @Override
                    public Boolean call(Boolean isNetworkAvailable) {
                        return isNetworkAvailable;
                    }
                }).
                flatMap(new Func1<Boolean, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(Boolean aBoolean) {
                        return mSignUpModel.isNetworkAvailable();
                    }
                }).
                subscribeOn(mRxSchedulers.internet()).
                observeOn(mRxSchedulers.androidThread()).
                subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d(e.toString());
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if(aBoolean){
                            //do firebase login
                            mSignUpView.registerClicks().subscribe(new Observer<UserDao>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(UserDao userDao) {
                                    mSignUpModel.registerToFireBase(userDao.getmName(),
                                            userDao.getmEmail(), userDao.getmPassword(),
                                            userDao.getmImageUrl());
                                }
                            });

                        }

                    }
                });
    }

    public void setImageUri(String uri){
        this.imageUri = uri;
    }

    public void onDestroy(){
        mSubscriptions.clear();
    }
}
