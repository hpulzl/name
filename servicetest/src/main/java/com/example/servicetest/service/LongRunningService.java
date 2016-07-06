package com.example.servicetest.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.servicetest.service.broadcast.AlarmBroadCastReceiver;

import java.util.Date;

/**
 * Created by lzl on 2016/7/6.
 */
public class LongRunningService extends Service {
    private static final String TAG="LongRunningService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"正在执行onStartCommand..");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //后台中执行的具体逻辑。这里只是打印时间
                Log.i(TAG,"当前时间:"+new Date().toString());
            }
        }).start();
        //设置一定的时间来进行激活
        alarmTimer();
        return super.onStartCommand(intent, flags, startId);
    }
    private void alarmTimer(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anMinute = 60 * 1000; //设置一分钟。
        long triggerAtTime = SystemClock.elapsedRealtime()+anMinute;
        Intent i = new Intent(this,AlarmBroadCastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,0,i,0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置精确时间，Android4.4 API19之后使用setExact
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        }else{
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        }
    }
}
