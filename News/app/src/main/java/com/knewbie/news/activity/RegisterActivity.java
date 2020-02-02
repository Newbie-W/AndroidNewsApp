package com.knewbie.news.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.knewbie.news.R;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextUname, editTextPwd, editTextRePwd;

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
                if (editTextUname.getText().toString().length() < 6) {
                    Toast.makeText(RegisterActivity.this, "用户名长度不能小于6", Toast.LENGTH_LONG).show();  //此处无法直接使用this
                }
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
    }
}
