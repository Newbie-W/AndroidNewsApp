package com.knewbie.news.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.knewbie.news.R;

import cn.jzvd.JzvdStd;

public class MyJzvdStd extends JzvdStd {
    public ImageView share;
    public MyJzvdStd(Context context) {
        super(context);
    }

    public MyJzvdStd(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
        share = findViewById(R.id.share);
        share.setOnClickListener(this);
    }

    @Override
    public void setScreenNormal() {
        super.setScreenNormal();
        share.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setScreenFullscreen() {
        super.setScreenFullscreen();
        share.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_std_with_share_button;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.share) {
            // TODO: 分享
            Intent intent = new Intent(Intent.ACTION_SEND);
            //intent.putExtra(Intent.EXTRA_SUBJECT, url);
            intent.setType("text/plain");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //startActivity(Intent.createChooser(intent, getTitle()));
        }
    }
}
