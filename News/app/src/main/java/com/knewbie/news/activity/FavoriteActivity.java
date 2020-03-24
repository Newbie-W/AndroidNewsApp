package com.knewbie.news.activity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

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
    private Toolbar toolbarTop;
    private final int REQUESTCODE_NEWSDETAIL = 1;
    private GlobalApplication app;
    private DatabaseOperationDao dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        initView();
    }

    private void initView() {
        toolbarTop = findViewById(R.id.toolbarFavorite);
        setSupportActionBar(toolbarTop);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        refresh();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(FavoriteActivity.this, "ItemClick:"+position+",id"+id, Toast.LENGTH_SHORT).show();
                //GlobalApplication app = (GlobalApplication) getApplication();
                //DatabaseOperationDao dbManager = app.getDatabaseOperationDao();
                Log.d("hello", "favorite click");
                NewsBean.ResultBean.DataBean dataBean = newsDisplayItemList.get(position);
                //String url = dataBean.getUrl();
                String newsId = dataBean.getUniquekey();
                Intent intent = new Intent(FavoriteActivity.this, NewsDetailActivity.class);
                //intent.putExtra("url", url);
                //intent.putExtra("newsId", newsId);
                intent.putExtra("dataBean", dataBean);
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
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Log.d("hello", "favorite click");
                //Toast.makeText(FavoriteActivity.this, "ItemLongClick:"+position+",id"+id, Toast.LENGTH_SHORT).show();
                if (userBean == null) {
                    app = (GlobalApplication) getApplication();
                    dbManager = app.getDatabaseOperationDao();
                    userBean = app.getUserBean();
                }
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(FavoriteActivity.this);
                dialogBuilder.setTitle("提示");
                dialogBuilder.setMessage("确认删除该条记录吗？");
                dialogBuilder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbManager.deleteNewsHistoryDisplayItem(dbManager.findNewsHistory(userBean.getId(), newsDisplayItemList.get(position).getUniquekey()));
                        refresh();
                    }
                });
                dialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogBuilder.show();
                return true;    //return false会回到单击事件的响应中
            }
        });
    }

    private void refresh() {
        listView = findViewById(R.id.listViewFavorite);
        if (userBean == null) {
            app = (GlobalApplication) getApplication();
            dbManager = app.getDatabaseOperationDao();
            userBean = app.getUserBean();
        }
        newsDisplayItemList = dbManager.getNewsFavoriteList(userBean.getId());
        adapter = new NewsForDisplayFavoriteAdapter(newsDisplayItemList, this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_with_search_top, menu);
        MenuItem item = menu.findItem(R.id.toolbarItem_search);
        SearchView searchView = (SearchView) item.getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(FavoriteActivity.this, query, Toast.LENGTH_LONG).show();
                //dbManager.searchNewsFavoriteList(userBean.getId(), query);
                searchNews(query);
                //Log.d("hello", "searchView"+query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchNews(newText);
                return false;
            }
        });
        return true;
    }

    private void searchNews(String query) {
        if (query==null || query.trim().isEmpty())
            newsDisplayItemList = dbManager.getNewsFavoriteList(userBean.getId());
        else {
            newsDisplayItemList = dbManager.searchNewsFavoriteList(userBean.getId(), query);
        }
        adapter = new NewsForDisplayFavoriteAdapter(newsDisplayItemList, FavoriteActivity.this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.toolbarItem_search:
                break;
            case R.id.toolbarItem_deleteAll:
                if (userBean == null) {
                    app = (GlobalApplication) getApplication();
                    dbManager = app.getDatabaseOperationDao();
                    userBean = app.getUserBean();
                }
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(FavoriteActivity.this);
                dialogBuilder.setTitle("提示");
                dialogBuilder.setMessage("确认删除该条记录吗？");
                dialogBuilder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbManager.deleteAllNewsFavoriteDisplay(userBean.getId());
                        refresh();
                    }
                });
                dialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogBuilder.show();
                break;
            default:
                break;
        }
        return true;
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
