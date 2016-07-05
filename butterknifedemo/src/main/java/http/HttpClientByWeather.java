package http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lzl on 2016/7/5.
 * 访问天气预报的接口
 */
public class HttpClientByWeather {
private static final String strPath = "http://wthrcdn.etouch.cn/WeatherApi?city=";
    public static String httpGet(String city){
        InputStream is = null;
        HttpURLConnection connection = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(strPath+city);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if(connection.getResponseCode()==200){
                is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while((line = br.readLine())!=null){
                    sb.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(is!=null) {
                    is.close();
                }
                if(connection!=null){
                    connection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    public String httpPost(String city){
        return null;
    }
}
