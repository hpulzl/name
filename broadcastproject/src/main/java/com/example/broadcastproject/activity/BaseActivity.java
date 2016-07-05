package com.example.broadcastproject.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.broadcastproject.R;
import com.example.broadcastproject.util.CollectionActivity;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        CollectionActivity.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CollectionActivity.removeActivity(this);
    }
}
