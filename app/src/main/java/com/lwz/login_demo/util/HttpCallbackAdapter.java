package com.lwz.login_demo.util;

import java.util.Map;

public abstract class HttpCallbackAdapter implements HttpCallbackListener{
    @Override
    public void onMsgFinish(String responseMsg) {

    }

    @Override
    public void onError(Exception response) {

    }


    public abstract void onEntityFinish(String responseMsg, String dataJson);
}
