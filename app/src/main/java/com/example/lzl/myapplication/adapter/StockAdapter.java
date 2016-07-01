package com.example.lzl.myapplication.adapter;

import android.support.v7.widget.RecyclerView;

import com.example.lzl.myapplication.R;
import com.example.lzl.myapplication.adapter.base.BaseRecyclerAdapter;
import com.example.lzl.myapplication.adapter.base.RecyclerViewHolder;
import com.example.lzl.myapplication.entity.Message;
import com.example.lzl.myapplication.entity.Stock;

import java.util.List;

/**
 * Created by lzl on 2016/6/26.
 */
public class StockAdapter extends BaseRecyclerAdapter<Message>{
    public StockAdapter(RecyclerView view, List<Message> datas) {
        super(view, datas, R.layout.item_main_stock);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Message item, int position, boolean isScrolling) {
        holder.setText(R.id.title_tv,item.title);
        holder.setText(R.id.source_tv,"来自:"+item.source);
        //收藏个数
        holder.setText(R.id.collectionNum_tv,item.likeCount+"");
        //股票信息
        holder.setText(R.id.name1_tv,item.stocks.get(0).name);
        holder.setText(R.id.symbol1_tv,item.stocks.get(0).symbol);
        holder.setText(R.id.name2_tv,item.stocks.get(1).name);
        holder.setText(R.id.symbol2_tv,item.stocks.get(1).symbol);
        holder.setText(R.id.name3_tv,item.stocks.get(2).name);
        holder.setText(R.id.symbol3_tv,item.stocks.get(2).symbol);
    }
}
