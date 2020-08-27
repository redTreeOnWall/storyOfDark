package com.lirunlong.storyofdark;

import android.os.Build;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

import androidx.appcompat.view.menu.ShowableListMenu;

public class HttpUtils {
    static void AskHttp(String  urlStr,OnHttpFinished finished){

        Thread askThreed = new Thread(() ->{
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(urlStr);
                connection = (HttpURLConnection) url.openConnection();
                //设置请求方法
                connection.setRequestMethod("GET");
                //设置连接超时时间（毫秒）
                connection.setConnectTimeout(5000);
                //设置读取超时时间（毫秒）
                connection.setReadTimeout(5000);

                //返回输入流
                InputStream in = connection.getInputStream();

                //读取输入流
                reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
//                show(result.toString());
                finished.onOk(true,result.toString());
            } catch (Exception e) {
                finished.onOk(true,e.toString());
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {//关闭连接
                    connection.disconnect();
                }
            }
        } );


        askThreed.start();
    }
    public interface OnHttpFinished{
        void onOk( boolean isSuc,String result);
    }
}
