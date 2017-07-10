package com.example.utsavstha.feedapp.screens.feeds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.utsavstha.feedapp.application.AppController;
import com.example.utsavstha.feedapp.screens.feeds.core.FeedsModel;
import com.example.utsavstha.feedapp.screens.feeds.core.FeedsPresenter;
import com.example.utsavstha.feedapp.screens.feeds.core.FeedsView;
import com.example.utsavstha.feedapp.screens.feeds.dagger.DaggerFeedsComponent;
import com.example.utsavstha.feedapp.screens.feeds.dagger.FeedsContextModule;
import com.example.utsavstha.feedapp.screens.feeds.dagger.FeedsModule;

import javax.inject.Inject;

public class FeedsActivity extends AppCompatActivity {


    @Inject
    FeedsView mFeedsView;

    @Inject
    FeedsPresenter mFeedsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerFeedsComponent.builder().appComponent(AppController.getAppComponent()).
                feedsContextModule(new FeedsContextModule(this))
                .build().inject(this);
        setContentView(mFeedsView.constructView());
        mFeedsPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFeedsPresenter.onDestroy();
    }
}
