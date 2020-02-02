package com.knewbie.news.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.knewbie.news.R;

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
    }

    private void register() {
        Intent intent = new Intent();
        intent.setClass(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void login() {
        String uname = editTextUname.getText().toString();
        String pwd = editTextPwd.getText().toString();
        if (uname.equals("0")&&pwd.equals("0") || uname.equals("admin")&&pwd.equals("admin")) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "登录失败，测试用户0", Toast.LENGTH_LONG).show();
        }
    }

}
