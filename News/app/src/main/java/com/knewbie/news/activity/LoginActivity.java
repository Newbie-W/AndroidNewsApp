package com.knewbie.news.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.knewbie.news.MainActivity;
import com.knewbie.news.R;
import com.knewbie.news.db.DatabaseOperationDao;
import com.knewbie.news.entity.UserBean;
import com.knewbie.news.global.GlobalApplication;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText editTextUname, editTextPwd;
    private TextView textViewRegister, textViewPhoneLogin;
    private String phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button)this.findViewById(R.id.buttonLogin);
        editTextUname = (EditText) this.findViewById(R.id.editTextLoginUsername);
        editTextPwd = (EditText) this.findViewById(R.id.editTextLoginPassword);
        textViewRegister = (TextView) this.findViewById(R.id.textViewRegisterLink);
        textViewPhoneLogin = this.findViewById(R.id.textView_phoneFastLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        textViewPhoneLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(LoginActivity.this, PhoneFastLoginActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("newsDatas", MODE_MULTI_PROCESS);
        String uname = sharedPreferences.getString("username", null);
        editTextUname.setText(uname);
    }

    private void register() {
        Intent intent = new Intent();
        intent.setClass(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void login() {
        String userInputName = editTextUname.getText().toString();
        String userInputPwd = editTextPwd.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("newsDatas", MODE_MULTI_PROCESS);
        String trueUname = sharedPreferences.getString("username", null);
        String truePwd = sharedPreferences.getString("user_password", null);
        GlobalApplication app = (GlobalApplication) this.getApplication();
        DatabaseOperationDao dbManager = app.getDatabaseOperationDao();
        UserBean userBean = dbManager.findUser(userInputName, userInputPwd);
        if (userInputName.equals(trueUname) && userInputPwd.equals(truePwd) || userBean !=  null) {
			//sharedPreferences.Editor editor = sharedPreferences.edit();
            if (trueUname == null || !trueUname.equals(userInputName)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", userInputName);
                editor.putString("user_password", userInputPwd);
                editor.apply();
            }
            this.finish();  //关闭原来界面
            Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            //intent.putExtra("userBean", userBean);      //UserBean需要序列化
            app.setUserBean(userBean);
            startActivity(intent);
        } else {
            Toast.makeText(this, "登录失败，用户名或密码不存在，提示：测试用户0", Toast.LENGTH_LONG).show();
        }
    }

}
