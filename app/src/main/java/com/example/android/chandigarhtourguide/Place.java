package com.example.android.chandigarhtourguide;

public class Place {
    private String mPlaceName;
    private int mThumbnail;
    private String mLikesCount;

    public Place(){}

    public Place(String name,int thumbnail,String count){
        mPlaceName = name;
        mThumbnail = thumbnail;
        mLikesCount = count;
    }

    public String getmLikesCount() {
        return mLikesCount;
    }

    public int getmThumbnail() {
        return mThumbnail;
    }

    public String getmPlaceName() {
        return mPlaceName;
    }

    public void setmLikesCount(String mLikesCount) {
        this.mLikesCount = mLikesCount;
    }

    public void setmPlaceName(String mPlaceName) {
        this.mPlaceName = mPlaceName;
    }

    public void setmThumbnail(int mThumbnail) {
        this.mThumbnail = mThumbnail;
    }
}
