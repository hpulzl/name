package com.example.broadcastproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.broadcastproject.R;

public class LoginActivity extends BaseActivity {
    private Button login;
    private EditText account,password;
    private Intent mIntent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        login = (Button) findViewById(R.id.login);
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void login(){
        if(account.getText().toString().equals("tony")
                &&password.getText().toString().equals("123qwe")){
            mIntent.setClass(this,MainActivity.class);
            startActivity(mIntent);
        }else{
            Toast.makeText(this,"用户名和密码不正确",Toast.LENGTH_SHORT).show();
        }
    }
}
