package com.lwz.login_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.lwz.login_demo.R;
import com.lwz.login_demo.activity.base.BaseActivity;
import com.lwz.login_demo.entity.user.UserEntity;
import com.lwz.login_demo.entity.user.UserTaskEntity;
import com.lwz.login_demo.util.HttpCallbackAdapter;
import com.lwz.login_demo.util.HttpUtil;
import com.lwz.login_demo.util.MsgConverter;
import com.lwz.login_demo.util.SysConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 任务页面
 */
public class MyTaskActivity extends BaseActivity {

    private List<UserTaskEntity> tasks;
    public void initDate() throws InterruptedException {
        Map<String,String> param=new HashMap<>();
        param.put("taskType",SysConstants.SmType.SHUIZHUN);
        String address = HttpUtil.addParamToGet(SysConstants.SmhUrl.GET_TASK_URL, param);
        final CountDownLatch latch=new CountDownLatch(1);
        HttpUtil.get(address,  new HttpCallbackAdapter() {
                    @Override
                    public void onEntityFinish(String responseMsg, String dataJson) {
                        showToast(responseMsg);
                        tasks =MsgConverter.parseData(dataJson,new TypeToken<List<UserTaskEntity>>(){}.getType());
                    }
                },latch);
        //给页面添加任务item
        latch.await();
        addTaskItem(tasks);


    }


    public void addTaskItem(List<UserTaskEntity> userTask){
        LinearLayout container = (LinearLayout) findViewById(R.id.task_container);
        LayoutInflater inflater = this.getLayoutInflater();
        for (UserTaskEntity userTaskEntity:userTask){
            LinearLayout view = (LinearLayout) inflater.inflate(R.layout.task_view, null);
            TextView newView = view.findViewById(R.id.task1);
            newView.setText(userTaskEntity.getTaskName());
            newView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v=(TextView)v;
                    toInfoPage(((TextView) v).getText().toString());
                }
            });
            container.addView(view);
        }
    }

    public void toInfoPage(String taskName){
        Intent intent = new Intent(this, TaskDetailActivity.class);
        intent.putExtra("taskName",taskName);
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_task);
        try {
            initDate();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


//    public void login(View view) {
//        startActivity(new Intent(this,LoginActivity.class));
//
//    }

    public void exit(View view) {
        finish();
    }

    public void addTask(View v){
        startActivity(new Intent(this,TaskInfoActivity.class));
    }

    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyTaskActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
