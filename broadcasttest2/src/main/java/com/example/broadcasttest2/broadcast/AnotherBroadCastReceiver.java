package com.example.broadcasttest2.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by lzl on 2016/7/5.
 */
public class AnotherBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"这是另一个广播接收器",Toast.LENGTH_SHORT).show();
    }
}
