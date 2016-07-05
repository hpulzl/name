package com.example.butterknifedemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.net.HttpURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import http.HttpClientByWeather;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mName_tv)
    TextView mNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mNameTv.setText("你好，Butterknife");
    }
}
