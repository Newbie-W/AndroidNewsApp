package com.knewbie.news.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.knewbie.news.entity.UserBean;
import com.knewbie.news.global.GlobalApplication;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ListView listView;
    private List<NewsBean.ResultBean.DataBean> newsDisplayItemList;
    private NewsForDisplayFavoriteAdapter adapter;
    private UserBean userBean;
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
                return false;
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
}
