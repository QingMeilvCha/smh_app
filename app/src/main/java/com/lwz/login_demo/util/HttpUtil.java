package com.lwz.login_demo.util;

import com.lwz.login_demo.entity.Entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpUtil {

    public static void sendHttpRequest(final String address,final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);

                    connection.setDoOutput(false);//这里设置为ture的话会自动转化为post方式
                    int responseCode = connection.getResponseCode();
                    if(responseCode==200){
                        InputStream in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        if (listener != null) {
                                // 回调onFinish()方法
                            listener.onFinish(response.toString());
                        }
                    }else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (listener != null) {
                        // 回调onError()方法
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
             }
            }).start();
        }


    public static void get(final String address,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                OkHttpClient client=new OkHttpClient();
                Request request = new Request.Builder()
                        .url(address)
                        .build();
                Response response=client.newCall(request).execute();
                String msg = response.body().string();

                    if(listener!=null){
                        listener.onFinish(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if(listener!=null){
                        listener.onError(e);
                    }
                }
            }
        }).start();
    }

    public static void post(final String address, final HttpCallbackListener listener, final Entity entity){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Map<String, Object> param = EntityToMap.entityToMap(entity);

                    FormBody.Builder builder = new FormBody.Builder();

                    Iterator<Map.Entry<String, Object>> iterator = param.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry<String, Object> next = iterator.next();
                        builder.add(next.getKey(),next.getValue().toString());
                    }
                    FormBody requestBody = builder.build();

                    OkHttpClient client=new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(address)
                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                    String msg = response.body().string();

                    if(listener!=null){
                        listener.onFinish(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if(listener!=null){
                        listener.onError(e);
                    }
                }
            }
        }).start();
    }
}
