package com.knewbie.news.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.knewbie.news.R;
import com.knewbie.news.entity.MessageItem;

import java.util.List;

public class InformationAdapter extends BaseAdapter {
    List<MessageItem> list;
    LayoutInflater layoutInflater;

    public InformationAdapter(List<MessageItem> list, Activity context) {
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.listview_chat_info_item, null);
        TextView textViewSender = view.findViewById(R.id.textViewInfoSenderName);
        TextView textViewInfo = view.findViewById(R.id.textViewChatInfo);
        TextView textViewTime = view.findViewById(R.id.textViewInfoTime);
        ImageView imageViewSender = view.findViewById(R.id.imageViewInfoSender);
        MessageItem item = list.get(position);
        textViewSender.setText(item.getUname());
        textViewInfo.setText(item.getMessage());
        textViewTime.setText(item.getTime());
        int icon = R.drawable.ic_appbar_user;
        if (icon > 0)
            imageViewSender.setImageResource(icon);
        return view;
    }
}
