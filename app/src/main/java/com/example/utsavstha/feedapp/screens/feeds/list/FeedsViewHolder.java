package com.example.utsavstha.feedapp.screens.feeds.list;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.utsavstha.feedapp.R;
import com.example.utsavstha.feedapp.models.FeedDao;
import jp.wasabeef.glide.transformations.MaskTransformation;


/**
 * Created by utsavstha on 7/9/17.
 */

public class FeedsViewHolder extends RecyclerView.ViewHolder {

    private ImageView mProfileImage;
    private ImageView mFeedImage;
    private TextView mUserName;
    private TextView mStatus;

    private View mView;

    public FeedsViewHolder(View itemView) {
        super(itemView);

        this.mView = itemView;
        mProfileImage = itemView.findViewById(R.id.iv_profile_image);
        mFeedImage = itemView.findViewById(R.id.iv_feed_image);
        mUserName = itemView.findViewById(R.id.tv_username);
        mStatus = itemView.findViewById(R.id.tv_status);
    }

    void bind(FeedDao feed) {
        Glide.with(mView.getContext()).load(feed.getmUserImageUrl())
                .override(60, 60)
                .bitmapTransform(new CenterCrop(mView.getContext()),
                        new MaskTransformation(mView.getContext(), R.drawable.ic_mask_star))
                .into(mProfileImage);

        Glide.with(mView.getContext()).load(feed.getmFeedImageUrl())
                .override(400, 200)
                .centerCrop()
                .into(mFeedImage);


        mUserName.setText(TextUtils.isEmpty(feed.getmUserName())
                ? "Missing Name" : feed.getmUserName());
        mStatus.setText(TextUtils.isEmpty(feed.getmStatus())
                ? "Missing Status" : feed.getmStatus());
    }
}
