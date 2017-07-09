package com.example.utsavstha.feedapp.application.builder;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by utsavstha on 7/9/17.
 */

@Module
public class ApplicationContextModule {
    private final Context mContext;

    public ApplicationContextModule(Context context){
        this.mContext = context;
    }

    @AppScope
    @Provides
    Context provideAppContext(){
        return mContext;
    }
}
