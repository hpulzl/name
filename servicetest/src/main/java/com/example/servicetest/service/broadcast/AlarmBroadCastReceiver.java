package com.example.servicetest.service.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.servicetest.service.LongRunningService;

/**
 * Created by lzl on 2016/7/6.
 */
public class AlarmBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent mIntent = new Intent(context, LongRunningService.class);
        context.startService(mIntent);
    }
}
