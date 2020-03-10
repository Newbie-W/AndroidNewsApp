package com.knewbie.news.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.knewbie.news.R;
import com.knewbie.news.adapter.FollowAdapter;
import com.knewbie.news.entity.FollowItem;

import java.util.ArrayList;
import java.util.List;

public class FollowActivity extends AppCompatActivity {

    private ListView listView;
    private List<FollowItem> followItemList;
    private FollowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        initView();
    }

    private void initView() {
        refresh();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FollowActivity.this, "ItemClick:"+position+",id"+id, Toast.LENGTH_SHORT).show();
                //Log.d("helloSubscribe","Test" );
            }
        });
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FollowActivity.this, "ItemSelected:"+position+",id"+id, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*listView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });*/
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FollowActivity.this, "ItemLongClick:"+position+",id"+id, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void refresh() {
        listView = findViewById(R.id.listViewSubscribe);
        followItemList = initList();
        adapter = new FollowAdapter(followItemList, this);
        listView.setAdapter(adapter);
    }

    private List<FollowItem> initList() {
        List<FollowItem> list = new ArrayList<>();
        for (int i=0; i<11; i++) {
            FollowItem item = new FollowItem();
            item.setId(i);
            item.setuName("被关注用户"+i);
            item.setSelfIntroduction(i+".这个人很懒，并没有留下什么。");
            //item.setAvatarAd();
            list.add(item);
        }
        return list;
    }
}
