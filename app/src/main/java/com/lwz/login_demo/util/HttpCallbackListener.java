package com.lwz.login_demo.util;

import com.lwz.login_demo.entity.Entity;

public interface HttpCallbackListener {

     void onEntityFinish(Entity entity);

     void onError(Exception response);

     void onMsgFinish(String msg);
}
