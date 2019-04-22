package com.lwz.login_demo.util;


public interface HttpCallbackListener {


     void onError(Exception response);

     void onMsgFinish(String responseMsg);
}
