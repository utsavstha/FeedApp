package com.example.utsavstha.feedapp.screens.signIn.core;

import com.example.utsavstha.feedapp.models.UserDao;
import com.example.utsavstha.feedapp.utils.rxUtils.RxSchedulers;
import com.google.firebase.auth.FirebaseAuth;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
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

    public SignInPresenter(SignInModel signInModel, SignInView signInView, RxSchedulers rxSchedulers, CompositeSubscription compositeSubscription) {
        this.mModel = signInModel;
        this.mRxSchedulers = rxSchedulers;
        this.mSubscription = compositeSubscription;
        this.mSignInView = signInView;
    }

    public void onCreate() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            mModel.gotoFeedsListActivity();
            return;
        }
        mSubscription.add(getFirebaseLoginSubscription());
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


    public void onDestroy() {
        mSubscription.clear();
    }


    private Subscription signIn(final UserDao userDao) {

        return mModel.isNetworkAvailable().filter(new Func1<Boolean, Boolean>() {
            @Override
            public Boolean call(Boolean isNetworkAvailable) {
                return isNetworkAvailable;
            }
        }).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean isNetworkAvailable) {
                mModel.loginFireBase(userDao.getmEmail(), userDao.getmPassword())
                        .observeOn(mRxSchedulers.androidThread())
                        .subscribeOn(mRxSchedulers.internet())
                        .subscribe(new Subscriber<Boolean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Boolean loggedIn) {
                                mModel.gotoFeedsListActivity();

                            }
                        });
            }
        });

    }

    private Subscription getFirebaseLoginSubscription() {
        return mSignInView.loginClicks().subscribeOn(mRxSchedulers.internet()).
                observeOn(mRxSchedulers.androidThread()).
                subscribe(new Observer<UserDao>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserDao userDao) {
                        mSubscription.add(signIn(userDao));
                    }
                });
    }


}
