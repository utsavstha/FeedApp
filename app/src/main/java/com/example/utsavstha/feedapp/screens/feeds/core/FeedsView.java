package com.example.utsavstha.feedapp.screens.feeds.core;

import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.utsavstha.feedapp.R;
import com.example.utsavstha.feedapp.models.FeedDao;
import com.example.utsavstha.feedapp.screens.feeds.FeedsActivity;
import com.example.utsavstha.feedapp.screens.feeds.list.FeedsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by utsavstha on 7/9/17.
 */

public class FeedsView {

    private RecyclerView mList;

    private View mView;

    private FeedsAdapter mAdapter;

    public FeedsView(FeedsActivity feedsActivity) {
        FrameLayout parent = new FrameLayout(feedsActivity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        mView = LayoutInflater.from(feedsActivity).inflate(R.layout.activity_feeds, parent, true);

        mList = mView.findViewById(R.id.rv_feeds);
        mAdapter = new FeedsAdapter();
        mList.setAdapter(mAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(feedsActivity);
        mList.setLayoutManager(mLayoutManager);
    }

    public View constructView() {
        return mView;
    }

    public void swapAdapter(List<FeedDao> feeds)
    {
        mAdapter.swapAdapter(feeds);
    }



}
