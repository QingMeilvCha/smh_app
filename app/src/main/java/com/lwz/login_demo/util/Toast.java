package com.lwz.login_demo.util;

import android.app.Activity;
import android.content.Context;


public class Toast extends Activity {

    public  void  showToast(final Context c,final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                android.widget.Toast.makeText(c, msg, android.widget.Toast.LENGTH_SHORT).show();
            }
        });
    }
}