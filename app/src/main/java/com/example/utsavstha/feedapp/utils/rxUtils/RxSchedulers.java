package com.example.utsavstha.feedapp.utils.rxUtils;

import rx.Scheduler;

/**
 * Created by utsavstha on 7/9/17.
 */

public interface RxSchedulers {

    Scheduler runOnBackground();

    Scheduler io();

    Scheduler compute();

    Scheduler androidThread();

    Scheduler internet();
}
