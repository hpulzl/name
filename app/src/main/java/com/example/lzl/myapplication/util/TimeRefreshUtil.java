package com.example.lzl.myapplication.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lzl on 2016/7/1.
 * 限制时间
 */
public class TimeRefreshUtil {
    /**
     * 将当前时间和规定的时间进行比较
     * @param currentTime
     * @return
     */
    public static boolean isRefresh(Date currentTime){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String time = sdf.format(currentTime);
        int startTimeH = 9;
        int endTimeH = 15;
        //获取当前时间的时和分
        String[] current = time.split(":");
        int currentH = Integer.parseInt(current[0]);
        //时小于9或者大于15不刷新。
        if(currentH < startTimeH || currentH > endTimeH){
            return false;
        }
        return true;
    }
}
