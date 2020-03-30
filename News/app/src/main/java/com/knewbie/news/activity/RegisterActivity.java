package com.knewbie.news.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.knewbie.news.R;
import com.knewbie.news.db.DatabaseOperationDao;
import com.knewbie.news.entity.UserBean;
import com.knewbie.news.global.GlobalApplication;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUname, editTextPwd, editTextRePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextUname = (EditText) this.findViewById(R.id.editTextRegisterUsername);
        editTextPwd = (EditText) this.findViewById(R.id.editTextRegisterPassword);
        editTextRePwd = (EditText) this.findViewById(R.id.editTextRegisterRePassword);

        editTextUname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (editTextUname.getText().toString().length() < 1) {
                    Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
                }//用户名长度不能小于6
            }
        });
        editTextPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (editTextPwd.getText().toString().length() < 6) {
                    Toast.makeText(RegisterActivity.this, "密码长度不能小于6", Toast.LENGTH_LONG).show();
                }
            }
        });
        editTextRePwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String pwd, pwdAgain;
                pwd = editTextPwd.getText().toString();
                pwdAgain = editTextRePwd.getText().toString();
                if (!pwd.equals(pwdAgain)) {
                    Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button registerButton = this.findViewById(R.id.buttonRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String name = editTextUname.getText().toString();
        String pwd = editTextPwd.getText().toString();
        String pwdAgain = editTextRePwd.getText().toString();
        if (name.length() < 1) {
            Toast.makeText(RegisterActivity.this, "用户名长度不能为空", Toast.LENGTH_LONG).show();
            return ;
        }
        if (pwd.length() < 6) {
            Toast.makeText(RegisterActivity.this, "密码长度不能小于6", Toast.LENGTH_LONG).show();
            return ;
        }
        if (!pwd.equals(pwdAgain)) {
            Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_LONG).show();
            return ;
        }
        GlobalApplication app = (GlobalApplication) this.getApplication();
        DatabaseOperationDao dbManager = app.getDatabaseOperationDao();
        if (! dbManager.findUsername(name)) {
            SharedPreferences sharedPreferences = this.getSharedPreferences("newsDatas", MODE_MULTI_PROCESS);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", name);
            editor.putString("user_password", pwd);
            //editor.commit();
            editor.apply();
            UserBean userBean = new UserBean(name, pwd);
            dbManager.addUser(userBean);
            finish();
            Toast.makeText(this, "注册成功，跳转回登陆界面", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "注册失败，该用户名已被注册", Toast.LENGTH_LONG).show();
        }


    }
}
