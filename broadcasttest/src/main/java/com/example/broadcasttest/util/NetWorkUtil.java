package com.example.broadcasttest.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by lzl on 2016/7/5.
 * 判断手机是什么网络
 */
public class NetWorkUtil {
    /**
     * 无效网络
     */
    public static final String NETWORK_TYPE_NONE="当前网络不可用";
    /**
     * 2G
     */
    public static final String NETWORK_TYPE_2G="2G网络";
    /**
     * 3G或3G以上，快速网络
     */
    public static final String NETWORK_TYPE_3G="3G网络";
    /**
     * wifi
     */
    public static final String NETWORK_TYPE_WIFI="WIFI网络";

    public static String getWorkType(Context context){
        String netType = "";
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netWorkInfo = connManager.getActiveNetworkInfo();
        if(netWorkInfo!=null && netWorkInfo.isAvailable()){
           String type = netWorkInfo.getTypeName();
            if(type.equalsIgnoreCase("WIFI")){
                netType = NETWORK_TYPE_WIFI;
            }else if(type.equalsIgnoreCase("MOBILE")){
                int nSubType = netWorkInfo.getSubtype();
                TelephonyManager mTelephony = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS
                        && !mTelephony.isNetworkRoaming()) {
                    netType = NETWORK_TYPE_2G;
                } else {
                    netType = NETWORK_TYPE_3G;
                }
            }
        }else {
            netType = NETWORK_TYPE_NONE;
        }
        return netType;
    }
}
