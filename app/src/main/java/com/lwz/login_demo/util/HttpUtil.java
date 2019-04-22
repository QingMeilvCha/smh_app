package com.lwz.login_demo.util;

import com.lwz.login_demo.entity.user.Entity;
import com.lwz.login_demo.entity.user.UserEntity;
import com.lwz.login_demo.entity.user.UserTaskEntity;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpUtil {

    public static void get(final String address,final HttpCallbackAdapter listener,final CountDownLatch latch){
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
                if (listener != null) {
                    if (adusResponse.getCode().equals(SysConstants.ResponseCode.SUCCESS)) {
                        listener.onEntityFinish(adusResponse.getMsg(), adusResponse.getDataJson());
                    }else{
                        listener.onError(new RuntimeException("状态码为"+ adusResponse.getCode()));
                    }
                }
                } catch (Exception e) {
                    if (listener != null) {
                        e.printStackTrace();
                        listener.onError(e);
                    }
                }finally {
                    latch.countDown();
                }
            }
        }).start();
    }

    public static void post(final String address, final HttpCallbackAdapter listener, final Entity entity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Map<String, Object> param = MsgConverter.entityToMap(entity);

                    FormBody.Builder builder = new FormBody.Builder();

                    Iterator<Map.Entry<String, Object>> iterator = param.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, Object> next = iterator.next();
                        builder.add(next.getKey(), next.getValue().toString());
                    }
                    FormBody requestBody = builder.build();

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(address)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String res = response.body().string();
                    AdusResponse adusResponse = MsgConverter.StringToAdusResponse(res);
                    if (listener != null) {
                        if (adusResponse.getCode().equals(SysConstants.ResponseCode.SUCCESS)) {
                            listener.onEntityFinish(adusResponse.getMsg(), adusResponse.getDataJson());
                        }
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                }
            }
        }).start();
    }

    public static void postJson(final String address, final HttpCallbackAdapter listener, final String json) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("param", json);
                    FormBody requestBody = builder.build();

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(address)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String res = response.body().string();
                    AdusResponse adusResponse = MsgConverter.StringToAdusResponse(res);
                    if (listener != null) {
                        if (adusResponse.getCode().equals(SysConstants.ResponseCode.SUCCESS)) {
                            listener.onEntityFinish(adusResponse.getMsg(), adusResponse.getDataJson());
                        }
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                }
            }
        }).start();
    }

    public static String addParamToGet(String url,Map<String,String> param){
        if(param.isEmpty()){
            return url;
        }else {
            Iterator<Map.Entry<String, String>> iterator = param.entrySet().iterator();
            int index=0;
            while (iterator.hasNext()){
                Map.Entry<String, String> entry = iterator.next();
                if(index == 0){
                    url=url+"?"+entry.getKey()+"="+entry.getValue()+"&";
                }else {
                    url=url+entry.getKey()+"="+entry.getValue()+"&";
                }
            }
            return url.substring(0,url.length()-1);
        }
    }
}
