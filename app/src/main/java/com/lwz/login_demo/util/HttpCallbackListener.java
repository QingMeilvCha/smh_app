package com.lwz.login_demo.util;

public interface HttpCallbackListener {

     void onFinish(String response);

     void onError(Exception response);
}
