package com.knewbie.news.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.knewbie.news.R;
import com.knewbie.news.db.DatabaseOperationDao;
import com.knewbie.news.entity.UserBean;
import com.knewbie.news.global.GlobalApplication;
import com.knewbie.news.widget.ItemGroup;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyInfoActivity extends AppCompatActivity {
    
    private CircleImageView imageViewAvatar;
    private RelativeLayout layoutImageViewAvatar;
    private ItemGroup itemGroupName, itemGroupPhone, itemGroupEmail, itemGroupSignature;
    private Toolbar toolbarTop;
    //private boolean hasChanged = false;
    private UserBean userBean;
    private String picPath;
    private boolean isUploadPic;    //标记是否上传图片
    private String SDCardPath;
    private final int WRITE_SD_CODE = 1;
    private final int READ_SD_CODE = 2;
    private final int CAMERA_PIC = 3;
    private final int ALBUM_PIC = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        if (!ImageLoader.getInstance().isInited())
            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        //userBean = (UserBean) getIntent().getSerializableExtra("userBean");
        GlobalApplication application = (GlobalApplication) getApplication();
        userBean = application.getUserBean();
        initView();
    }

    private void initView() {
        imageViewAvatar = findViewById(R.id.imageView_avatar);
        layoutImageViewAvatar = findViewById(R.id.layout_imgView_avatar);
        itemGroupName = findViewById(R.id.itemGroup_name);
        itemGroupPhone = findViewById(R.id.itemGroup_phone);
        itemGroupEmail = findViewById(R.id.itemGroup_email);
        itemGroupSignature = findViewById(R.id.itemGroup_signature);
        toolbarTop = findViewById(R.id.toolbarTop_myInfo);
        if (userBean.getAvatar() != null)
            ImageLoader.getInstance().displayImage(userBean.getAvatar(), imageViewAvatar, getOption());
        else
            ImageLoader.getInstance().displayImage("drawable://"+R.drawable.ic_appbar_user, imageViewAvatar, getOption());
        itemGroupName.setText(userBean.getUsername());
        itemGroupPhone.setText(userBean.getPhone());
        itemGroupEmail.setText(userBean.getEmailAd());
        itemGroupSignature.setText(userBean.getSignature());
        setSupportActionBar(toolbarTop);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);     //显示toolbar的返回
        layoutImageViewAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyInfoActivity.this, "更改头像", Toast.LENGTH_SHORT).show();
                addAvatar();
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
                        //changeInfo(editText, "用户名", "username");
                        String input = editText.getText().toString();
                        if (input == null || input.equals("")){

                        }else {
                            GlobalApplication globalApplication = (GlobalApplication) getApplication();
                            DatabaseOperationDao dbManager = globalApplication.getDatabaseOperationDao();
                            if (dbManager.findUsername(input))
                                Toast.makeText(MyInfoActivity.this, "修改失败，用户名已被注册，请重新输入", Toast.LENGTH_SHORT).show();
                            else {
                                dbManager.updateTable("user_table", "username =  '"+input+"'", "user_id = "+userBean.getId());
                                userBean.setUsername(editText.getText().toString());
                                itemGroupName.setText(userBean.getUsername());
                            }
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
                Toast.makeText(MyInfoActivity.this, "暂时无法更改手机号", Toast.LENGTH_SHORT).show();
                //Log.d("hello", "Phone Change");
            }
        });
        itemGroupEmail.setOnItemClickListener(new ItemGroup.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {
                final EditText editText = new EditText(MyInfoActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(MyInfoActivity.this);
                builder.setTitle("更改邮箱");
                //builder.setIcon();
                final String email = userBean.getEmailAd();
                editText.setHint(email);
                builder.setView(editText);
                builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeInfo(editText, "邮箱", "email_address");
						userBean.setEmailAd(editText.getText().toString());
                        itemGroupEmail.setText(userBean.getEmailAd());
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
        itemGroupSignature.setOnItemClickListener(new ItemGroup.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {
                // TODO: signature以后改为跳转至新界面填写
                final EditText editText = new EditText(MyInfoActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(MyInfoActivity.this);
                builder.setTitle("更改个性签名");
                //builder.setIcon();
                final String signature = userBean.getSignature();
                editText.setHint(signature);
                builder.setView(editText);
                builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeInfo(editText, "个性签名", "signature");
                        userBean.setSignature(editText.getText().toString());
                        itemGroupSignature.setText(userBean.getSignature());
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
    }

    private void addAvatar() {
        //打开相册，读取图片，加载显示
        // choosePicSource();
        // TODO: 后期完善，选择相册还是拍照
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_SD_CODE);
        } else {
            Log.d("hello", "pick Pic");
            openAlbum();
        }
        /*String state = Environment.getExternalStorageState();   //获取外部存储设备的状态
        if (state.equals((Environment.MEDIA_MOUNTED))) {    //打开相机

        } else {
            Toast.makeText(this, "请确保已插入SD卡", Toast.LENGTH_SHORT).show();
        }*/
    }

    private void openAlbum() {
        /*
        or use:
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        */
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, ALBUM_PIC);
    }

    private void choosePicSource() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //View view = UI
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_SD_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //camera
            } else {
                Toast.makeText(this, "已禁止权限", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == READ_SD_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Log.d("hello", "onRequestPermissionsResult---read sd code");
                //album
                openAlbum();
            } else {
                Toast.makeText(this, "已禁止权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void changeInfo(EditText editText, String nameString, String colomn) {
        String input = editText.getText().toString();
        if (input == null || input.equals("")) {     // TODO: 暂时设为，空则不更改。后期如果此处删除，则方法形参也需更改了
            //Toast.makeText(MyInfoActivity.this, nameString+"不能为空！", Toast.LENGTH_SHORT).show();
            //return;
        } else {      // TODO: 暂时设为不核查格式是否正确
            GlobalApplication globalApplication = (GlobalApplication) getApplication();
            DatabaseOperationDao dbManager = globalApplication.getDatabaseOperationDao();
            dbManager.updateTable("user_table", colomn+" =  '"+input+"'", "user_id = "+userBean.getId());
            //userBean.setUsername(input);
        }
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //if (hasChanged) {
                Intent intent = new Intent();
                //intent.putExtra("userBean", userBean);
                GlobalApplication application = (GlobalApplication) getApplication();
                application.setUserBean(userBean);
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
            //intent.putExtra("userBean", userBean);
            GlobalApplication application = (GlobalApplication) getApplication();
            application.setUserBean(userBean);
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
        switch (requestCode) {
            case CAMERA_PIC:
                if (resultCode == RESULT_OK) {
                    Log.d("hello", "PicPath: "+picPath);
                    if (picPath != null) {
                        File file = new File(picPath);
                        //加载
                        isUploadPic = true;
                    }
                } else {
                    Toast.makeText(this, "放弃拍照", Toast.LENGTH_SHORT).show();
                }
                break;
            case ALBUM_PIC:
                //Log.d("hello", "onActivityResult---ALBUM-1");
                if (resultCode == RESULT_OK) {
                    //Log.d("hello", "onActivityResult---ALBUM-2");
                    if (data != null) {
                        //Log.d("hello", "onActivityResult---ALBUM-3-data!=null");
                        Uri uri = data.getData();
                        //Log.d("hello", "onActivityResult---ALBUM-4-uri"+uri.toString());
                        if (uri != null) {
                            SDCardPath = Environment.getExternalStorageDirectory().getPath()+"/";
                            picPath = uri.getPath();
                            if (picPath.startsWith("/raw/")) {
                                picPath = picPath.replaceFirst("/raw/", "");
                            }
                            if (picPath.startsWith("/external_files/")) {
                                picPath = picPath.replaceFirst("/external_files/", ""+SDCardPath);
                            }
                            picPath = "file://"+ picPath;
                            ImageLoader.getInstance().displayImage(picPath, imageViewAvatar, getOption());
                            isUploadPic = true;
                            userBean.setAvatar(picPath);
                            GlobalApplication globalApplication = (GlobalApplication) getApplication();
                            DatabaseOperationDao dbManager = globalApplication.getDatabaseOperationDao();
                            dbManager.updateTable("user_table", "pic =  '"+picPath+"'", "user_id = "+userBean.getId());
                            //Log.d("hello", "onActivityResult---ALBUM-5,---"+uri.getPath());
                            /*Cursor cursor = getContentResolver().query(uri, null, null, null,null);//找到图片
                            if (cursor!= null && cursor.moveToFirst()) {
                                picPath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                                Log.d("hello", "onActivityResult---ALBUM-5"+picPath);
                                //File file = new File(picPath);
                                ImageLoader.getInstance().displayImage(picPath, imageViewAvatar);
                                isUploadPic = true;
                            }
                            cursor.close();*/
                        }
                    }
                    /*Log.d("hello", "PicPath: "+picPath);
                    if (picPath != null) {
                        File file = new File(picPath);
                        //加载
                        isUploadPic = true;
                    }*/
                } else {
                    Toast.makeText(this, "放弃选择图片", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public static DisplayImageOptions getOption() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // .showImageOnLoading(R.drawable.logo_news_fill_2)    // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)         // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)              // 设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(true)                       // default 设置图片在加载前是否重置、复位
                .delayBeforeLoading(1000)                           // 下载前的延迟时间
                .cacheInMemory(true)                                // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)                                  // default  设置下载的图片是否缓存在SD卡中
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)   // default 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)                // default 设置图片的解码类型
                .build();

        return options;
    }
}
