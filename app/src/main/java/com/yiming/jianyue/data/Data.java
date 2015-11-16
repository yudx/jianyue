package com.yiming.jianyue.data;

import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Data<Type> {

    private static final String FIELD_PS = "ps";
    private static final String FIELD_PNO = "pno";
    private static final String FIELD_TOTAL_PAGE = "totalPage";
    private static final String FIELD_LIST = "list";


    @SerializedName(FIELD_PS)
    private int mP;
    @SerializedName(FIELD_PNO)
    private int mPno;
    @SerializedName(FIELD_TOTAL_PAGE)
    private int mTotalPage;
    @SerializedName(FIELD_LIST)
    private List<Type> mLists;


    public Data(){

    }

    public void setP(int P) {
        mP = P;
    }

    public int getP() {
        return mP;
    }

    public void setPno(int pno) {
        mPno = pno;
    }

    public int getPno() {
        return mPno;
    }

    public void setTotalPage(int totalPage) {
        mTotalPage = totalPage;
    }

    public int getTotalPage() {
        return mTotalPage;
    }

    public void setLists(List<Type> lists) {
        mLists = lists;
    }

    public List<Type> getLists() {
        return mLists;
    }

    @Override
    public String toString(){
        return "P = " + mP + ", pno = " + mPno + ", totalPage = " + mTotalPage + ", lists = " + mLists;
    }


}