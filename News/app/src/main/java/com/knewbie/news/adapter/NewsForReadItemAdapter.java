package com.knewbie.news.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.knewbie.news.R;
import com.knewbie.news.entity.NewsForReadItem;

import java.util.List;

public class NewsForReadItemAdapter extends BaseAdapter {
    private List<NewsForReadItem> mItems;
    private LayoutInflater mInflater;

    public NewsForReadItemAdapter(List<NewsForReadItem> mItems, Activity context) {
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
        View view = mInflater.inflate(R.layout.listview_news_for_read_item, null);
        TextView textViewTitle = (TextView)view.findViewById(R.id.textViewTitle_listview);
        TextView textViewAuthor = (TextView) view.findViewById(R.id.textViewAuthor_listview);
        TextView textViewIntroduction = (TextView) view.findViewById(R.id.textViewIntroduction_listview);
        ImageView imageViewNewsPic = (ImageView) view.findViewById(R.id.imageViewNewsPic_listview);
        TextView textViewReadAmount = (TextView) view.findViewById(R.id.textViewReadAmount_listview);
        TextView textViewLikeAmount = (TextView) view.findViewById(R.id.textViewLikeAmount_listview);
        TextView textViewReviewAmount = (TextView) view.findViewById(R.id.textViewReviewAmount_listview);
        TextView textViewLastEditTime = (TextView) view.findViewById(R.id.textViewLastEditTime_listview);
        NewsForReadItem item = mItems.get(position);
        textViewTitle.setText(item.getTitle());
        textViewAuthor.setText(item.getAuthor());
        textViewIntroduction.setText(item.getIntroduction());
        textViewLikeAmount.setText(String.valueOf(item.getLikeAmount()));
        textViewReadAmount.setText(String.valueOf(item.getReadAmount()));
        textViewReviewAmount.setText(String.valueOf(item.getReviewAmount()));
        textViewLastEditTime.setText(item.getLastEditTime());
        int icon = R.drawable.logo_news_fill_2;
        if (icon > 0)   //有此图片，就设置出来
            imageViewNewsPic.setImageResource(icon);
        return view;
    }
}
