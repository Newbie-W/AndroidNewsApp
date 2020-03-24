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
import com.knewbie.news.entity.UserBean;
import com.knewbie.news.global.GlobalApplication;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ListView listView;
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
        setContentView(R.layout.activity_history);
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
                //Toast.makeText(HistoryActivity.this, "ItemClick:"+position+",id"+id, Toast.LENGTH_SHORT).show();
                NewsBean.ResultBean.DataBean dataBean = newsDisplayItemList.get(position);
                Intent intent = new Intent(HistoryActivity.this, NewsDetailActivity.class);
                intent.putExtra("dataBean", dataBean);
                startActivityForResult(intent, REQUESTCODE_NEWSDETAIL);
            }
        });
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HistoryActivity.this, "ItemSelected:"+position+",id"+id, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //Toast.makeText(HistoryActivity.this, "ItemLongClick:"+position+",id"+id, Toast.LENGTH_SHORT).show();
                if (userBean == null) {
                    app = (GlobalApplication) getApplication();
                    dbManager = app.getDatabaseOperationDao();
                    userBean = app.getUserBean();
                }
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(HistoryActivity.this);
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
                return true;
            }
        });
    }

    private void refresh() {
        listView = findViewById(R.id.listViewHistory);
        if (userBean == null) {
            app = (GlobalApplication) getApplication();
            dbManager = app.getDatabaseOperationDao();
            userBean = app.getUserBean();
        }
        newsDisplayItemList = dbManager.getNewsHistoryList(userBean.getId());
        Log.d("hello", "userid:"+userBean.getId()+", listSize = "+newsDisplayItemList.size());
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
                Toast.makeText(HistoryActivity.this, query, Toast.LENGTH_LONG).show();
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
            newsDisplayItemList = dbManager.getNewsHistoryList(userBean.getId());
        else {
            newsDisplayItemList = dbManager.searchNewsHistoryList(userBean.getId(), query);
        }
        adapter = new NewsForDisplayFavoriteAdapter(newsDisplayItemList, HistoryActivity.this);
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
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(HistoryActivity.this);
                dialogBuilder.setTitle("提示");
                dialogBuilder.setMessage("确认删除该条记录吗？");
                dialogBuilder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbManager.deleteAllNewsHistoryDisplay(userBean.getId());
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
}
