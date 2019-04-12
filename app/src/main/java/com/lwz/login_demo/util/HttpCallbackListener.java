package com.lwz.login_demo.util;


public interface HttpCallbackListener {

     void onEntityFinish(String responseMsg,Object e);

     void onError(Exception response);

     void onMsgFinish(String responseMsg);
}
