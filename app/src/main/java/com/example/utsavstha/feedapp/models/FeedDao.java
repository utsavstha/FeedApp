package com.example.utsavstha.feedapp.models;

/**
 * Created by utsavstha on 7/9/17.
 */

public class FeedDao {

    private int mFeedId;
    private String mUserId;
    private String mUserName;
    private String mStatus;
    private String mUserImageUrl;
    private String mFeedImageUrl;


    public FeedDao() {
    }

    public FeedDao(int mFeedId, String mFeedImageUrl, String mStatus, String mUserId, String mUserImageUrl, String mUserName){
        this.mFeedId = mFeedId;
        this.mUserId = mUserId;
        this.mUserName = mUserName;
        this.mStatus = mStatus;
        this.mUserImageUrl = mUserImageUrl;
        this.mFeedImageUrl = mFeedImageUrl;
    }


    public int getmFeedId() {
        return mFeedId;
    }

    public void setmFeedId(int mFeedId) {
        this.mFeedId = mFeedId;
    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmUserImageUrl() {
        return mUserImageUrl;
    }

    public void setmUserImageUrl(String mUserImageUrl) {
        this.mUserImageUrl = mUserImageUrl;
    }

    public String getmFeedImageUrl() {
        return mFeedImageUrl;
    }

    public void setmFeedImageUrl(String mFeedImageUrl) {
        this.mFeedImageUrl = mFeedImageUrl;
    }
}
