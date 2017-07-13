package com.example.utsavstha.feedapp.screens.feeds.core;

import com.example.utsavstha.feedapp.models.FeedDao;
import com.example.utsavstha.feedapp.utils.rxUtils.RxSchedulers;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by utsavstha on 7/9/17.
 */

public class FeedsPresenter {

    private FeedsModel mFeedsModel;
    private FeedsView mFeedsView;
    private RxSchedulers mRxSchedulers;
    private CompositeSubscription mSubscription;
    public FeedsPresenter(RxSchedulers schedulers, FeedsModel model, FeedsView view, CompositeSubscription subscriptions) {
        this.mFeedsModel = model;
        this.mFeedsView = view;
        this.mRxSchedulers = schedulers;
        this.mSubscription = subscriptions;
    }

    public void onCreate(){
        mSubscription.add(getList());
    }


    private Subscription getList() {

        return mFeedsModel.getFeedsList()
                .subscribeOn(mRxSchedulers.internet())
                .observeOn(mRxSchedulers.androidThread())
                .subscribe(new Subscriber<List<FeedDao>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<FeedDao> feedDaos) {
                mFeedsView.swapAdapter(feedDaos);
            }
        });
    }

    public void onDestroy() {
        mSubscription.clear();
    }
}
