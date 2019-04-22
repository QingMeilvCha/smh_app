package com.lwz.login_demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lwz.login_demo.R;

public class TaskDetailActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String taskName = intent.getStringExtra("taskName");
        

        setContentView(R.layout.task_detail);
    }

}
