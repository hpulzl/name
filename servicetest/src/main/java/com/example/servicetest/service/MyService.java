package com.example.servicetest.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import com.example.servicetest.R;

public class MyService extends Service {
    private static final String TAG="MyService";
    private DownloadBinder mBinder = new DownloadBinder();
    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.i(TAG,"开始执行下载任务..");
        }
        public int getProgress(){
            Log.i(TAG,"获取下载进度..");
            return 0;
        }
    }
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate..");
        startNotification();
    }

    private void startNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("显示通知");
        builder.setContentTitle("通知");
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true); //打开程序后图标消失
        Intent mIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,mIntent,0);
        builder.setContentIntent(pendingIntent);
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        }
        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand..");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy..");
    }
}
