package com.example.broadcasttest.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by lzl on 2016/7/5.
 */
public class MyBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"这是自定义的标准",Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}
