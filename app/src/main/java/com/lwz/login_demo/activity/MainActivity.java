package com.lwz.login_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.lwz.login_demo.R;
import com.lwz.login_demo.activity.base.BaseActivity;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }


    public void login(View view) {
        startActivity(new Intent(this,LoginActivity.class));

    }

    public void exit(View view) {
        finish();
    }

}
