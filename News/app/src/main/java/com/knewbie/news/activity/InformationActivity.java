package com.knewbie.news.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.knewbie.news.R;
import com.knewbie.news.adapter.InformationAdapter;
import com.knewbie.news.entity.MessageItem;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity {

    private ListView listView;
    private List<MessageItem> messageItemList;
    private InformationAdapter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        initView();
    }

    private void initView() {
        refresh();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(InformationActivity.this, "ItemClick:"+position+",id"+id, Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(InformationActivity.this, "ItemSelected:"+position+",id"+id, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(InformationActivity.this, "ItemLongClick:"+position+",id"+id, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void refresh() {
        listView = findViewById(R.id.listViewInformation);
        messageItemList = initList();
        adpter = new InformationAdapter(messageItemList, this);
        listView.setAdapter(adpter);
    }

    private List<MessageItem> initList() {
        List<MessageItem> list = new ArrayList<>();
        for (int i=0; i<5; i++) {
            MessageItem item = new MessageItem();
            item.setId(i);
            item.setUname("News用户"+i);
            item.setTime("2020-07-0"+(i+1));
            item.setMessage("这是一段信息。这是一段信息。这是一段信息。");
            //item.setAvatarAd();
            list.add(item);
        }
        return list;
    }
}
