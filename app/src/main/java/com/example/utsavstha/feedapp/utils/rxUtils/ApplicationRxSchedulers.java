package com.example.utsavstha.feedapp.utils.rxUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by utsavstha on 7/9/17.
 */

public class ApplicationRxSchedulers implements RxSchedulers{
    private static Executor mBackgroundExecutor = Executors.newCachedThreadPool();
    private static Scheduler mBACKGROUND_SCHEDULERS = Schedulers.from(mBackgroundExecutor);
    private static Executor mInternetExecutor = Executors.newCachedThreadPool();
    private static Scheduler mINTERNET_SCHEDULERS = Schedulers.from(mInternetExecutor);

    @Override
    public Scheduler runOnBackground() {
        return mBACKGROUND_SCHEDULERS;
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler compute() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler androidThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler internet() {
        return mINTERNET_SCHEDULERS;
    }
}
