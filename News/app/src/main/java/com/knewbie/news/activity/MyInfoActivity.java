package com.knewbie.news.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.knewbie.news.R;
import com.knewbie.news.db.DatabaseOperationDao;
import com.knewbie.news.entity.UserBean;
import com.knewbie.news.global.GlobalApplication;
import com.knewbie.news.widget.ItemGroup;

public class MyInfoActivity extends AppCompatActivity {
    
    //private CircleImageView imageViewAvatar;
    private RelativeLayout layoutImageViewAvatar;
    private ItemGroup itemGroupName, itemGroupPhone, itemGroupEmail, itemGroupSignature;
    private Toolbar toolbarTop;
    private boolean hasChanged = false;
    private UserBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        initView();
        userBean = (UserBean) getIntent().getSerializableExtra("userBean");
    }

    private void initView() {
        //imageViewAvatar = findViewById(R.id.imageView_avatar);
        layoutImageViewAvatar = findViewById(R.id.layout_imgView_avatar);
        itemGroupName = findViewById(R.id.itemGroup_name);
        itemGroupPhone = findViewById(R.id.itemGroup_phone);
        itemGroupEmail = findViewById(R.id.itemGroup_email);
        itemGroupSignature = findViewById(R.id.itemGroup_signature);
        toolbarTop = findViewById(R.id.toolbarTop_myInfo);
        setSupportActionBar(toolbarTop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);     //显示toolbar的返回
        layoutImageViewAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyInfoActivity.this, "更改头像", Toast.LENGTH_SHORT).show();
            }
        });
        itemGroupName.setOnItemClickListener(new ItemGroup.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {
                final EditText editText = new EditText(MyInfoActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(MyInfoActivity.this);
                builder.setTitle("更改用户名");
                //builder.setIcon();
                final String name = userBean.getUsername();
                editText.setHint(name);
                builder.setView(editText);
                builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String input = editText.getText().toString();
                        if (input == null || input.equals(""))
                            Toast.makeText(MyInfoActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                        else {
                            userBean.setUsername(input);
                            GlobalApplication globalApplication = (GlobalApplication) getApplication();
                            DatabaseOperationDao dbManager = globalApplication.getDatabaseOperationDao();

                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        itemGroupPhone.setOnItemClickListener(new ItemGroup.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {
                Toast.makeText(MyInfoActivity.this, "更改手机号联系方式", Toast.LENGTH_SHORT).show();
                //Log.d("hello", "Phone Change");
            }
        });
        itemGroupEmail.setOnItemClickListener(new ItemGroup.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {

            }
        });
        itemGroupSignature.setOnItemClickListener(new ItemGroup.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {

            }
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //if (hasChanged) {
                Intent intent = new Intent();
                intent.putExtra("userBean", userBean);
                setResult(1, intent);
                //}
                this.finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("userBean", userBean);
            setResult(1, intent);
            this.finish();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
