package com.yiming.jianyue.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiming.jianyue.R;
import com.yiming.jianyue.data.WeixinArticles;
import com.yiming.jianyue.ui.WebviewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：jianyue
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：15/11/16 下午11:17
 * 修改人：wengyiming
 * 修改时间：15/11/16 下午11:17
 * 修改备注：
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListVH> implements ArticleListVH.ArticleItemClickListener {
    private static final String TAG = "ArticleListAdapter";

    public void setLists(final List<WeixinArticles> mLists) {
        lists = mLists;
    }

    private List<WeixinArticles> lists = new ArrayList<>();
    private Activity mActivity;
    private ArticleListVH.ArticleItemClickListener listener;

    public ArticleListAdapter(List<WeixinArticles> lists) {
        this.lists = lists;
    }

    public ArticleListAdapter(List<WeixinArticles> lists, Activity mActivity) {
        this(lists);
        this.mActivity = mActivity;
        setListener(this);
    }

    public ArticleListAdapter(List<WeixinArticles> lists, Activity mActivity, int channnel) {
        this(lists, mActivity);
        setListener(this);
    }

    @Override
    public ArticleListVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ArticleListVH vh = new ArticleListVH(v, listener);
        vh.title = (TextView) v.findViewById(R.id.title);
        vh.content = (TextView) v.findViewById(R.id.content);
        vh.mImageView = (ImageView) v.findViewById(R.id.image);
        return vh;
    }

    @Override
    public void onBindViewHolder(ArticleListVH holder, int position) {
        final WeixinArticles article = lists.get(position);
        if (article == null) {
            return;
        }
        holder.title.setText(article.getTitle());
        holder.content.setText(String.valueOf(article.getSource()));
        Glide.with(mActivity)
                .load(article.getFirstImg())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(holder.mImageView);
    }


    @Override
    public int getItemCount() {
        return lists.size();
    }

    public ArticleListVH.ArticleItemClickListener getListener() {
        return listener;
    }

    public void setListener(ArticleListVH.ArticleItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(View view, int position) {
        WeixinArticles article = lists.get(position);
        Intent mIntent = new Intent(mActivity, WebviewActivity.class);
        mIntent.putExtra("title", article.getTitle());
        mIntent.putExtra("url", article.getUrl());
        mActivity.startActivity(mIntent);
    }

    @Override
    public void onItemLongClick(View view, int position) {
    }
}
