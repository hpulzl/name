package com.example.broadcastproject.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;

import com.example.broadcastproject.R;
import com.example.broadcastproject.broadcast.ForceBroadCastReceive;

public class MainActivity extends BaseActivity {
    private Button forceBtn;
    private LocalBroadcastManager localBroadcastManager;
    private ForceBroadCastReceive forceBroadCastReceive;
    private IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        forceBtn = (Button) findViewById(R.id.force_btn);
        forceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                broadCastManager();
            }
        });
    }

    /**
     * 进行发送和接受广播
     */
    private void broadCastManager(){
        localBroadcastManager = LocalBroadcastManager.getInstance(MainActivity.this);
        Intent mInent = new Intent("com.example.broadcastproject.FORCE_QUIT");
        //发送一个自定义的广播
        localBroadcastManager.sendBroadcast(mInent);
        intentFilter = new IntentFilter("com.example.broadcastproject.FORCE_QUIT");
        forceBroadCastReceive = new ForceBroadCastReceive();
        //接受一个广播
        localBroadcastManager.registerReceiver(forceBroadCastReceive,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(forceBroadCastReceive);
    }
}
