package com.example.lzl.myapplication.activity;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.example.lzl.myapplication.R;
import com.example.lzl.myapplication.adapter.StockAdapter;
import com.example.lzl.myapplication.entity.Message;
import com.example.lzl.myapplication.entity.Stock;
import com.example.lzl.myapplication.util.AssetsUtil;
import com.example.lzl.myapplication.util.HSJsonUtil;
import com.example.lzl.myapplication.util.TimeRefreshUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;

public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;
    private StockAdapter mStockAdapter;
    private List<Message> messageList = new ArrayList<>();
    private final static String TAG = "Main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //直接开启线程
        new Thread(mRunable).start();
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLinearLayout = new LinearLayoutManager(this);
        mLinearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayout);
        mStockAdapter = new StockAdapter(mRecyclerView,messageList);
        mRecyclerView.setAdapter(mStockAdapter);
        firstRefresh();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        initData();
    }

    private void initData() {
        //开始下拉刷新
        setRefreshState(true);
        //加载数据
        List<Message> list = HSJsonUtil.getMessage(AssetsUtil.getJson(this,"data.json"));
        android.os.Message msg = new android.os.Message();
        msg.obj = list;
        Log.i(TAG,"list:"+list.toString());
        Log.i(TAG,"listSize:"+list.size());
        mHandler.sendMessage(msg);
    }
    private android.os.Handler mHandler = new android.os.Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            messageList.clear();
            messageList = (List<Message>) msg.obj;
            mStockAdapter.setData(messageList);
            setRefreshState(false);
            mStockAdapter.notifyDataSetChanged();
        }
    };
    private void firstRefresh(){
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        //这个是解决第一次进入首页时，没有产生下拉刷新的动画
        mSwipeRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,
                        getResources().getDisplayMetrics()));
    }
    private void setRefreshState(boolean flag){
        mSwipeRefreshLayout.setRefreshing(flag);
    }

    @Override
    public void onRefresh() {
        initData();
    }
    private Runnable mRunable =new Runnable() {
        @Override
        public void run() {
            //判断时间为9点和15点之间。
            if (TimeRefreshUtil.isRefresh(new Date())) {
                initData();
                mHandler.postDelayed(this, 10000);
                Log.i(TAG,"打印时间....shijian区间内..");
            }else {
                Log.i(TAG,"打印时间....时间区间外");
                return;
            }
        }
    };
}
