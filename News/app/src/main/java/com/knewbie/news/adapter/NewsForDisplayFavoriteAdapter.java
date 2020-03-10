package com.knewbie.news.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.knewbie.news.R;
import com.knewbie.news.entity.NewsDisplayItem;

import java.util.List;

public class NewsForDisplayFavoriteAdapter extends BaseAdapter {
    private List<NewsDisplayItem> mItems;
    private LayoutInflater mInflater;

    public NewsForDisplayFavoriteAdapter(List<NewsDisplayItem> mItems, Activity context) {
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
        return mItems.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.listview_news_for_display_favorite_item, null);
        TextView textViewTitle = (TextView)view.findViewById(R.id.textViewTitle_Favorite);
        TextView textViewAuthor = (TextView) view.findViewById(R.id.textViewAuthor_Favorite);
        TextView textViewLastEditTime = (TextView) view.findViewById(R.id.textViewLastEditTime_Favorite);
        TextView textViewIntroduction = (TextView) view.findViewById(R.id.textViewIntroduction_Favorite);
        NewsDisplayItem item = mItems.get(position);
        textViewTitle.setText(item.getTitle());
        textViewAuthor.setText(item.getAuthor());
        textViewLastEditTime.setText(item.getLastEditTime());
        textViewIntroduction.setText(item.getIntroduction());
        return view;
    }
}
