package com.lwz.login_demo.activity.base;

import android.app.Activity;
import android.os.Bundle;

import com.lwz.login_demo.activity.control.ActivityCollector;

public class BaseActivity extends Activity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

}
