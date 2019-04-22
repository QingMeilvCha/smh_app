package com.lwz.login_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lwz.login_demo.R;

public class ChoiceFunctionActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_function);
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showToast("home");
                    return true;
                case R.id.navigation_dashboard:
                    showToast("dashboard");
                    return true;
                case R.id.navigation_notifications:
                    showToast("title_notifications");
                    return true;
            }
            return false;
        }
    };

    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ChoiceFunctionActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void nextActivity(View view){
        startActivity(new Intent(this,MyTaskActivity.class));
    }

}
