package com.knewbie.news.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.knewbie.news.R;
import com.knewbie.news.entity.FollowItem;

import java.util.List;

public class FollowAdapter extends BaseAdapter {

    private List<FollowItem> list;
    private LayoutInflater layoutInflater;
    private AlertDialog.Builder dialogBuilder;

    public FollowAdapter(List<FollowItem> list, Activity context) {
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        dialogBuilder = new AlertDialog.Builder(context);
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
        View view = layoutInflater.inflate(R.layout.listview_subscribe_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewSubscribe);
        TextView textViewName = (TextView) view.findViewById(R.id.textViewSubscribeName);
        TextView textViewSelfIntro = (TextView) view.findViewById(R.id.textViewSubscribeSelfIntro);
        FollowItem item = list.get(position);
        int icon = R.drawable.ic_appbar_user;
        if (icon > 0)
            imageView.setImageResource(icon);
        textViewName.setText(item.getuName());
        textViewSelfIntro.setText(item.getSelfIntroduction());
        Button buttonSubscribe = (Button)view.findViewById(R.id.buttonSubscribe);
        buttonSubscribe.setTag(item.getId());
        buttonSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = (int)v.getTag();
                dialogBuilder.setTitle("提示");
                dialogBuilder.setMessage("确认取消关注吗？");
                dialogBuilder.setPositiveButton("取消关注", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        unfollow(id);
                    }
                });
                dialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogBuilder.show();
            }
        });
        return view;
    }

    private void unfollow(int id) {
        for (int i=list.size()-1; i>=0; i--) {
            if (list.get(i).getId() == id)
                list.remove(i);
        }
        this.notifyDataSetChanged();    //刷新数据
    }
}
