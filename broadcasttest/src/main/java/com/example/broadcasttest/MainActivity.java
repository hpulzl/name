package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.broadcasttest.broadcast.LocalReceiver;
import com.example.broadcasttest.util.NetWorkUtil;

/**
 * 写一个动态注册广播的实例
 * 该实例用来监听网络的变化，当网络改变时，会通过广播发送提示。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private IntentFilter intentFilter;
    private NetChangeBroadCast netChangeBroadCast;
    private Button sendBtn,localBtn,orderBtn;
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initBroadCast();
    }

    private void initView() {
        sendBtn = (Button) findViewById(R.id.send_btn);
        localBtn = (Button) findViewById(R.id.local_btn);
        orderBtn = (Button) findViewById(R.id.order_btn);

        orderBtn.setOnClickListener(this);
        localBtn.setOnClickListener(this);
        sendBtn.setOnClickListener(this);
    }

    private void initBroadCast() {
        intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        netChangeBroadCast = new NetChangeBroadCast();
        registerReceiver(netChangeBroadCast,intentFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_btn:
                mIntent = new Intent("com.example.broadcasttest.MY_BROADCAST");
                sendBroadcast(mIntent);
                break;
            case R.id.local_btn:
                localBroadcastManager = LocalBroadcastManager.getInstance(this);
                mIntent = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(mIntent);
                intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
                localReceiver = new LocalReceiver();
                localBroadcastManager.registerReceiver(localReceiver,intentFilter);
                break;
            case R.id.order_btn:
                mIntent = new Intent("com.example.broadcasttest.MY_BROADCAST");
                //发送有序广播
                sendOrderedBroadcast(mIntent,null);
                break;
        }

    }

    class NetChangeBroadCast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this,NetWorkUtil.getWorkType(context),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netChangeBroadCast);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }
}
