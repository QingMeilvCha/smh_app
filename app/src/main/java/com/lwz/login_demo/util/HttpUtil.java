package com.lwz.login_demo.util;

import com.lwz.login_demo.entity.Entity;

import java.io.IOException;

import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpUtil {

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
                AdusResponse adusResponse =MsgConverter.StringToAdusResponse(msg);
                    if(listener!=null){
                        listener.onMsgFinish(adusResponse.getMsg(),adusResponse.getData());
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

                    Map<String, Object> param = MsgConverter.entityToMap(entity);

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
                    String res= response.body().string();
                    AdusResponse adusResponse = MsgConverter.StringToAdusResponse(res);
                        if(listener!=null){
                            listener.onMsgFinish(adusResponse.getMsg());
                        }
                        } catch (Exception e) {
                            e.printStackTrace();
                        if(listener!=null){
                            listener.onError(e);
                        }
                }
            }
        }).start();
    }
}
