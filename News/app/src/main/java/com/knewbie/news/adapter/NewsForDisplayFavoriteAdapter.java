package com.knewbie.news.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.knewbie.news.R;
import com.knewbie.news.entity.NewsBean;

import java.util.List;

public class NewsForDisplayFavoriteAdapter extends BaseAdapter {
    private List<NewsBean.ResultBean.DataBean> mItems;
    private LayoutInflater mInflater;

    public NewsForDisplayFavoriteAdapter(List<NewsBean.ResultBean.DataBean> mItems, Activity context) {
        this.mItems = mItems;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*@Override
    public long getItemId(int position) {
        return mItems.get(position).getId();
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.listview_news_for_display_favorite_item, null);
        TextView textViewTitle = (TextView)view.findViewById(R.id.textViewTitle_Favorite);
        TextView textViewAuthor = (TextView) view.findViewById(R.id.textViewAuthor_Favorite);
        TextView textViewLastEditTime = (TextView) view.findViewById(R.id.textViewLastEditTime_Favorite);
        //TextView textViewIntroduction = (TextView) view.findViewById(R.id.textViewIntroduction_Favorite);
        NewsBean.ResultBean.DataBean item = mItems.get(position);
        textViewTitle.setText(item.getTitle());
        textViewAuthor.setText(item.getAuthor_name());
        textViewLastEditTime.setText(item.getDate());
        //textViewIntroduction.setText(item.getUrl());
        return view;
    }
}
