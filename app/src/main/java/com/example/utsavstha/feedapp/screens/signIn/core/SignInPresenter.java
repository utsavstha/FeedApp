package com.example.utsavstha.feedapp.screens.signIn.core;

import com.example.utsavstha.feedapp.models.UserDao;
import com.example.utsavstha.feedapp.utils.rxUtils.RxSchedulers;
import com.google.firebase.auth.FirebaseAuth;

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

public class SignInPresenter {

    private SignInModel mModel;
    private SignInView mSignInView;
    private RxSchedulers mRxSchedulers;
    private CompositeSubscription mSubscription;

    public SignInPresenter(SignInModel signInModel, SignInView signInView,RxSchedulers rxSchedulers, CompositeSubscription compositeSubscription){
        this.mModel = signInModel;
        this.mRxSchedulers = rxSchedulers;
        this.mSubscription = compositeSubscription;
        this.mSignInView = signInView;
    }

    public void onCreate(){
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            mModel.gotoFeedsListActivity();
            return;
        }
        mSubscription.add(signIn());
        mSubscription.add(register());
    }

    private Subscription register() {
        return mSignInView.registerClicks().subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                mModel.gotoSignUpActivity();
            }
        });
    }



    public void onDestroy(){
        mSubscription.clear();
    }


    private Subscription signIn(){
        return mModel.isNetworkAvailable().doOnNext(new Action1<Boolean>() {
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
                        return mModel.isNetworkAvailable();
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
                            mSignInView.loginClicks().subscribe(new Observer<UserDao>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(UserDao userDao) {
                                    mModel.loginFireBase(userDao.getmEmail(), userDao.getmPassword());
                                }
                            });

                        }

                    }
                });
    }


}
