package com.knewbie.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.knewbie.news.R;
import com.knewbie.news.adapter.NewsForDisplayFavoriteAdapter;
import com.knewbie.news.db.DatabaseOperationDao;
import com.knewbie.news.entity.NewsBean;
import com.knewbie.news.entity.NewsDisplayItem;
import com.knewbie.news.entity.UserBean;
import com.knewbie.news.global.GlobalApplication;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    private ListView listView;
    //private NewsDisplayItem[] items = new NewsDisplayItem[6];
    private List<NewsBean.ResultBean.DataBean> newsDisplayItemList;
    private NewsForDisplayFavoriteAdapter adapter;
    private UserBean userBean;
    private final int REQUESTCODE_NEWSDETAIL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        initView();
    }

    private void initView() {
        refresh();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(FavoriteActivity.this, "ItemClick:"+position+",id"+id, Toast.LENGTH_SHORT).show();
                GlobalApplication app = (GlobalApplication) getApplication();
                DatabaseOperationDao dbManager = app.getDatabaseOperationDao();
                NewsBean.ResultBean.DataBean dataBean = newsDisplayItemList.get(position);
                String url = dataBean.getUrl();
                String newsId = dataBean.getUniquekey();
                Intent intent = new Intent(FavoriteActivity.this, NewsDetailActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("newsId", newsId);
                startActivityForResult(intent, REQUESTCODE_NEWSDETAIL);
            }
        });
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {       //selected：光标指向的记录
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FavoriteActivity.this, "ItemSelected:"+position+",id"+id, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FavoriteActivity.this, "ItemLongClick:"+position+",id"+id, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void refresh() {
        listView = findViewById(R.id.listViewFavorite);
        GlobalApplication app = (GlobalApplication) getApplication();
        DatabaseOperationDao dbManager = app.getDatabaseOperationDao();
        userBean = app.getUserBean();
        newsDisplayItemList = dbManager.getNewsFavoriteList(userBean.getId());
        adapter = new NewsForDisplayFavoriteAdapter(newsDisplayItemList, this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUESTCODE_NEWSDETAIL:
                if (resultCode == RESULT_OK) {
                    refresh();
                }
        }
    }

    private List<NewsDisplayItem> initList() {
        List<NewsDisplayItem> list = new ArrayList<>();
        for (int i=0; i<5; i++) {
            NewsDisplayItem item = new NewsDisplayItem();
            item.setId(i);
            item.setTitle("被收藏的新闻标题"+i);
            item.setAuthor("News官方作者");
            item.setLastEditTime("2020-07-0"+(i+1));
            //item.setCategory("");
            //item.setIntroduction("这是一段引言，这是一段引言，这是一段引言，这是一段引言。这是一段引言，这是一段引言，这是一段引言，这是一段引言。这是一段引言，这是一段引言，这是一段引言，这是一段引言。这是一段引言，这是一段引言，这是一段引言，这是一段引言。");
            item.setContent("引言：...。内容：...。");
            list.add(item);
        }
        return list;
    }
}
