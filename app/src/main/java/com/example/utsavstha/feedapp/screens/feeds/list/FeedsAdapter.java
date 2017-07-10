package com.example.utsavstha.feedapp.screens.feeds.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utsavstha.feedapp.R;
import com.example.utsavstha.feedapp.models.FeedDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by utsavstha on 7/9/17.
 */

public class FeedsAdapter extends RecyclerView.Adapter<FeedsViewHolder>{

    private List<FeedDao> mFeeds = new ArrayList<>();

    @Override
    public FeedsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_feeds, parent, false);
        return new FeedsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedsViewHolder holder, int position) {

        FeedDao feedDao = mFeeds.get(position);
        holder.bind(feedDao);
    }

    @Override
    public int getItemCount() {
        if (mFeeds != null && mFeeds.size() > 0) {
            return mFeeds.size();
        } else {
            return 0;
        }
    }

    public void swapAdapter(List<FeedDao> feeds) {
        this.mFeeds.clear();
        this.mFeeds.addAll(feeds);
        notifyDataSetChanged();
    }
}
