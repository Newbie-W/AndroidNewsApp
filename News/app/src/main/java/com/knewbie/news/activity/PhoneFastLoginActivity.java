package com.knewbie.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.knewbie.news.MainActivity;
import com.knewbie.news.R;
import com.knewbie.news.db.DatabaseOperationDao;
import com.knewbie.news.entity.UserBean;
import com.knewbie.news.global.GlobalApplication;
import com.mob.MobSDK;

import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class PhoneFastLoginActivity extends AppCompatActivity {

    private Button btnGetPwd, btnLogin;
    private EditText editTextPhone, editTextPwd;
    private TextView textViewSimpleLogin, textViewPwd, textViewChangePhoneLogin;
    private String APPKEY = "2e54ced420158";
    private String APPSECRET = "7f5c1039257770779e974508fe36cce5";
    private String phoneNum;
    private boolean flagPhoneWithPwd = false;
    private int time = 60;
    private final int STORE_USER = 0;
    private final int CHANGE_BUTTON_GETTING_PWD = 1;
    private final int CHANGE_BUTTON_GET_PWD = 2;
    private final int EVENT_PWD = 3;
    private Handler phoneLoginHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case STORE_USER:
                    //UserBean userBean = (UserBean) msg.obj;
                    //phoneN = userBean.getPhone();
                    break;
                case CHANGE_BUTTON_GETTING_PWD:
                    btnGetPwd.setText("重新发送("+time+")");
                    break;
                case CHANGE_BUTTON_GET_PWD:
                    btnGetPwd.setText("获取验证码");
                    btnGetPwd.setClickable(true);
                    time = 60;
                    break;
                case EVENT_PWD:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        //回调完成
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                            //提交验证码成功
                            Toast.makeText(PhoneFastLoginActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
                            login();
                            /*Intent intent = new Intent();
                            setResult();
                            intent.putExtra();
                            finish();*/
                        }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                            //data == true则，此手机号之前使用过
                            Log.d("hello", "data = "+data);
                            //获取验证码成功
                            Toast.makeText(PhoneFastLoginActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                        }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                            //返回支持发送验证码的国家列表
                        } else {
                            Toast.makeText(PhoneFastLoginActivity.this, "发送验证码失败", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(PhoneFastLoginActivity.this, "验证失败,"+data.toString(), Toast.LENGTH_LONG).show();
                        ((Throwable)data).printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_fast_login);
        btnGetPwd = this.findViewById(R.id.button_fastLogin_getPassword);
        btnLogin = this.findViewById(R.id.button_fastLogin_login);
        editTextPhone = this.findViewById(R.id.editText_fastLogin_phoneNum);
        editTextPwd = this.findViewById(R.id.editText_fastLogin_password);
        textViewSimpleLogin = this.findViewById(R.id.textView_fastLogin_simpleLogin);
        textViewPwd = findViewById(R.id.textView_fastLogin_password);
        textViewChangePhoneLogin = findViewById(R.id.textView_fastLogin_phoneWithPwd);

        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                phoneNum = editTextPhone.getText().toString();
            }
        });
        btnGetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPassword();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagPhoneWithPwd) login();
                else {
                    String pwd = editTextPwd.getText().toString();
                    SMSSDK.submitVerificationCode("86", phoneNum, pwd);
                }

            }
        });
        textViewSimpleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(PhoneFastLoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        textViewChangePhoneLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagPhoneWithPwd) {     //原来为手机号、密码登录，现在改为手机号验证码方式
                    btnGetPwd.setVisibility(View.VISIBLE);
                    textViewPwd.setVisibility(View.GONE);
                    textViewChangePhoneLogin.setText("手机号密码登录");
                    editTextPwd.setHint("请输入验证码");
                    flagPhoneWithPwd = false;
                } else {
                    btnGetPwd.setVisibility(View.GONE);
                    textViewPwd.setVisibility(View.VISIBLE);
                    textViewChangePhoneLogin.setText("手机号验证码登录");
                    editTextPwd.setHint("请输入密码");
                    flagPhoneWithPwd = true;
                }
            }
        });

        MobSDK.init(this, APPKEY, APPSECRET);

        //启动短信验证sdk
        final EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int i, int i1, Object o) {   // 三形参 event, result, data
                //super.afterEvent(i, i1, o);
                Message message = new Message();
                message.arg1 = i;
                message.arg2 = i1;
                message.obj = o;
                message.what = EVENT_PWD;
                phoneLoginHandler.sendMessage(message);
            }
        };
        //注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);

        /*SharedPreferences sharedPreferences = getSharedPreferences("newsDatas", MODE_PRIVATE);
        String uname = sharedPreferences.getString("username", null);
        editTextPhone.setText(uname);*/
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    private void getPassword() {
        phoneNum = editTextPhone.getText().toString();
        if (!isPhoneNum(phoneNum)) {        //是否为手机号
            Toast.makeText(PhoneFastLoginActivity.this, "请输入正确的手机号！", Toast.LENGTH_LONG).show();
            return ;
        }
        SMSSDK.getVerificationCode("86", phoneNum);     //通过sdk发短信
        btnGetPwd.setClickable(false);
        btnGetPwd.setText("重新发送("+time+")");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; time>0; time--) {
                    phoneLoginHandler.sendEmptyMessage(CHANGE_BUTTON_GETTING_PWD);
                    if (time == 0) {
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        Log.d("hello", ""+e.getMessage());
                    }
                }
                phoneLoginHandler.sendEmptyMessage(CHANGE_BUTTON_GET_PWD);
            }
        }).start();
    }

    private boolean isPhoneNum(String phoneNum) {
        String telRegex = "[1][358]\\d{9}";
        if (phoneNum.length() == 11) {
            return phoneNum.matches(telRegex);
        }
        return false;
    }

    private void login() {
        GlobalApplication app = (GlobalApplication) this.getApplication();
        DatabaseOperationDao dbManager = app.getDatabaseOperationDao();
        UserBean userBean = dbManager.findUserByPhone(phoneNum);
        if (flagPhoneWithPwd) {
            if (userBean == null) {
                Toast.makeText(this, "该手机号尚未注册！", Toast.LENGTH_SHORT).show();
                return;
            } else if (!editTextPwd.getText().toString().equals(userBean.getPassword())) {
                Toast.makeText(this, "登录失败，密码错误", Toast.LENGTH_LONG).show();
                return;
            }
        } else {
            if (userBean == null) {
                Toast.makeText(this, "注册用户", Toast.LENGTH_SHORT).show();
                userBean = register();
            }/* else {}*/
        }

        Log.d("hello", "hello, "+userBean.getUsername());
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userBean", userBean);
        startActivity(intent);
        this.finish();
        //UserBean userBean = dbManager.findUser(phoneNum, pwd);
        /*if (phoneNum.equals(trueUname) && userInputPwd.equals(truePwd) || userBean !=  null) {
            //sharedPreferences.Editor editor = sharedPreferences.edit();
            this.finish();  //关闭原来界面
            Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("userBean", userBean);      //UserBean需要序列化
            startActivity(intent);
        } else {
            Toast.makeText(this, "登录失败，用户名或密码不存在，提示：测试用户0", Toast.LENGTH_LONG).show();
        }*/
    }

    private UserBean register() {
        UserBean userBean = new UserBean();
        userBean.setPhone(phoneNum);
        String name;// = "user"+randomString(5)+"_"+phoneNum;
        GlobalApplication app = (GlobalApplication) this.getApplication();
        DatabaseOperationDao dbManager = app.getDatabaseOperationDao();
        do {
            name = "user" + randomString(6) + "_" + phoneNum;
        }while (dbManager.findUsername(name));
        userBean.setUsername(name);
        userBean.setPassword("123456");
        userBean.setPhone(phoneNum);
        userBean.setSignature("这个人很懒，并没有留下什么。");
        dbManager.addUser(userBean);
        return userBean;
    }

    private String randomString(int len) {
        String str = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random(System.currentTimeMillis());
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0; i<len; i++) {
            int n = random.nextInt(str.length());
            stringBuffer.append(str.charAt(n));
        }
        return stringBuffer.toString();
    }
}