package com.example.broadcastproject.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzl on 2016/7/5.
 * 管理Activity的Util类
 */
public class CollectionActivity {
    public static List<Activity> activityList = new ArrayList<>();
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }
    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }
    public static void finishAll(){
        for(Activity activity : activityList){
            if(!activity.isFinishing())
                activity.finish();
        }
    }
}
