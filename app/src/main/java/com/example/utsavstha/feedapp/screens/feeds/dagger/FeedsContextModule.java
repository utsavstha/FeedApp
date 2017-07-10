package com.example.utsavstha.feedapp.screens.feeds.dagger;

import com.example.utsavstha.feedapp.screens.feeds.FeedsActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by utsavstha on 7/10/17.
 */

@Module
public class FeedsContextModule {
    FeedsActivity mFeedsActivity;

    public FeedsContextModule(FeedsActivity feedsActivity){
        this.mFeedsActivity = feedsActivity;
    }

    @FeedsScope
    @Provides
    FeedsActivity providesFeedsContext(){
        return mFeedsActivity;
    }
}
