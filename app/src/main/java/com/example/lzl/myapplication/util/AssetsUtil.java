package com.example.lzl.myapplication.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lzl on 2016/6/26.
 * 从assets文件夹中获取json数据
 */
public class AssetsUtil {
    /**
     * 从从assets文件夹的指定文件中
     * 获取json数据
     * @param cxt  上下文对象
     * @param fileName 文件名称
     * @return
     */
    public static String getJson(Context cxt,String fileName){
        StringBuilder sb = new StringBuilder();
        AssetManager am = cxt.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
            Log.i("string","sb:"+sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        return sb.toString().trim();
    }
}
