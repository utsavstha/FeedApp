package com.example.utsavstha.feedapp.screens.feeds.dagger;

import com.example.utsavstha.feedapp.screens.feeds.FeedsActivity;
import com.example.utsavstha.feedapp.screens.feeds.core.FeedsModel;
import com.example.utsavstha.feedapp.screens.feeds.core.FeedsPresenter;
import com.example.utsavstha.feedapp.screens.feeds.core.FeedsView;
import com.example.utsavstha.feedapp.utils.rxUtils.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by utsavstha on 7/9/17.
 */
@Module
public class FeedsModule {

    @FeedsScope
    @Provides
    FeedsView provideView(FeedsActivity feedsActivity) {
        return new FeedsView(feedsActivity);
    }

    @FeedsScope
    @Provides
    FeedsPresenter providePresenter(RxSchedulers schedulers, FeedsView view, FeedsModel model) {
        CompositeSubscription subscriptions = new CompositeSubscription();
        return new FeedsPresenter(schedulers, model, view, subscriptions);
    }

    @FeedsScope
    @Provides
    FeedsModel provideModel(FeedsActivity feedsActivity) {
        return new FeedsModel(feedsActivity);
    }
}
