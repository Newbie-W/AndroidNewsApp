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

    Button btnLogin;
    EditText editTextUname, editTextPwd;
    TextView textViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button)this.findViewById(R.id.buttonLogin);
        editTextUname = (EditText) this.findViewById(R.id.editTextLoginUsername);
        editTextPwd = (EditText) this.findViewById(R.id.editTextLoginPassword);
        textViewRegister = (TextView) this.findViewById(R.id.textViewRegisterLink);
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

        SharedPreferences sharedPreferences = getSharedPreferences("newsDatas", MODE_PRIVATE);
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
        SharedPreferences sharedPreferences = getSharedPreferences("newsDatas", MODE_PRIVATE);
        String trueUname = sharedPreferences.getString("username", null);
        String truePwd = sharedPreferences.getString("user_password", null);
        GlobalApplication app = (GlobalApplication) this.getApplication();
        DatabaseOperationDao dbManager = app.getDatabaseOperationDao();
        UserBean userBean = dbManager.findUser(userInputName, userInputPwd);
        if (userInputName.equals(trueUname) && userInputPwd.equals(truePwd) || userBean !=  null) {
			//sharedPreferences.Editor editor = sharedPreferences.edit();
            this.finish();  //关闭原来界面
            Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("userBean", userBean);      //UserBean需要序列化
            startActivity(intent);
        } else {
            Toast.makeText(this, "登录失败，用户名或密码不存在，提示：测试用户0", Toast.LENGTH_LONG).show();
        }
    }

}
