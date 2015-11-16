package com.yiming.jianyue.data;

import com.google.gson.annotations.SerializedName;


public class WeixinArticles {

    private static final String FIELD_ID = "id";
    private static final String FIELD_SOURCE = "source";
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_URL = "url";
    private static final String FIELD_FIRST_IMG = "firstImg";
    private static final String FIELD_MARK = "mark";


    @SerializedName(FIELD_ID)
    private String mId;
    @SerializedName(FIELD_SOURCE)
    private String mSource;
    @SerializedName(FIELD_TITLE)
    private String mTitle;
    @SerializedName(FIELD_URL)
    private String mUrl;
    @SerializedName(FIELD_FIRST_IMG)
    private String mFirstImg;
    @SerializedName(FIELD_MARK)
    private String mMark;


    public WeixinArticles(){

    }

    public void setId(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public String getSource() {
        return mSource;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setFirstImg(String firstImg) {
        mFirstImg = firstImg;
    }

    public String getFirstImg() {
        return mFirstImg;
    }

    public void setMark(String mark) {
        mMark = mark;
    }

    public String getMark() {
        return mMark;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof WeixinArticles){
            return ((WeixinArticles) obj).getId() == mId;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return mId.hashCode();
    }

    @Override
    public String toString(){
        return "id = " + mId + ", source = " + mSource + ", title = " + mTitle + ", url = " + mUrl + ", firstImg = " + mFirstImg + ", mark = " + mMark;
    }


}