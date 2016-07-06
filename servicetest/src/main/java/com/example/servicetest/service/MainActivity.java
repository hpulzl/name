package com.example.servicetest.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.servicetest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.start_btn)
    Button startBtn;
    @BindView(R.id.stop_btn)
    Button stopBtn;
    @BindView(R.id.bind_btn)
    Button bindBtn;
    @BindView(R.id.unbind_btn)
    Button unbindBtn;
    @BindView(R.id.start_intent_btn)
    Button startIntentBtn;
    @BindView(R.id.start_alarm_btn)
    Button startAlarmBtn;
    private Intent mIntent, bindIntent;
    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initOnClick();
    }

    private void initOnClick() {
        submit(startBtn);
        submit(stopBtn);
        submit(bindBtn);
        submit(unbindBtn);
        submit(startIntentBtn);
        submit(startAlarmBtn);
    }

    @OnClick({R.id.start_btn, R.id.stop_btn, R.id.bind_btn,
            R.id.unbind_btn,R.id.start_intent_btn,R.id.start_alarm_btn})
    public void submit(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                Log.i("Tag", "打开服务");
                mIntent = new Intent(this, MyService.class);
                startService(mIntent);
                break;
            case R.id.stop_btn:
                Log.i("Tag", "关闭服务");
                stopService(mIntent);
                break;
            case R.id.bind_btn:
                Log.i("Tag", "绑定服务");
                bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);  //绑定服务
                break;
            case R.id.unbind_btn:
                Log.i("Tag", "解除服务");
                unbindService(serviceConnection); //解除绑定
                break;
            case R.id.start_intent_btn:
                Log.i("Main","当前主线程id:"+Thread.currentThread().getId());
                mIntent = new Intent(this,MyIntentService.class);
                startService(mIntent);
                break;
            case R.id.start_alarm_btn:
                Log.i("Main","启动后台定时任务");
                mIntent = new Intent(this,LongRunningService.class);
                startService(mIntent);
                break;
            default:
                break;
        }
    }

}
