package com.lwz.login_demo.util;

public interface HttpCallbackListener {

     void onEntityFinish(String responseMsg,Object o);

     void onError(Exception response);

     void onMsgFinish(String responseMsg,Object o);
     void onMsgFinish(String responseMsg);
}
