package com.lwz.login_demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lwz.login_demo.R;
import com.lwz.login_demo.entity.shuizhun.LPointClass;
import com.lwz.login_demo.entity.shuizhun.LineClass;
import com.lwz.login_demo.entity.shuizhun.result.CalculateResult;
import com.lwz.login_demo.entity.user.UserTaskEntity;
import com.lwz.login_demo.util.HttpCallbackAdapter;
import com.lwz.login_demo.util.HttpUtil;
import com.lwz.login_demo.util.MsgConverter;
import com.lwz.login_demo.util.SysConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.v4.view.ViewCompat.generateViewId;

public class TaskInfoActivity extends Activity {

    private int pointAddCount=1;
    private int lineAddCount=1;
    private Map<String,Integer> ids =new HashMap<>();
    private CalculateResult result=new CalculateResult();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_info);
    }


    public void calculate(View v){

        showToast("计算成功");
        //找到控件 获取数据
        EditText task_name = findViewById(R.id.task_name);
        String taskName=task_name.getText().toString();
        List<LPointClass> ControlPoints=new ArrayList<>();
        List<LineClass> CurrentSegments=new ArrayList<>();
        List<LPointClass> CurrentPoints = new ArrayList<>();
        for(int i=1;i<pointAddCount;i++){
            LPointClass point=new LPointClass();

            EditText Pid = findViewById(ids.get("Pid"+i+""));
            EditText PH = findViewById(ids.get("PH"+i+""));
            EditText P_Know = findViewById(ids.get("P_Know"+i+""));
            EditText P_Un_Konw = findViewById(ids.get("P_Un_Konw"+i+""));


            point.setPid(Pid.getText().toString());
            point.setH(Double.parseDouble(PH.getText().toString()));
            point.setControlP(P_Know.getText().toString().equals("1")?true:false);
            point.setH0(P_Know.getText().toString().equals("1")?true:false);
            ControlPoints.add(point);
        }

        for(int i=1;i<lineAddCount;i++){
            LineClass line=new LineClass();

            EditText Lid = findViewById(ids.get("Lid"+i+""));
            EditText start_point = findViewById(ids.get("start_point"+i+""));
            EditText end_point = findViewById(ids.get("end_point"+i+""));
            EditText H_diff = findViewById(ids.get("H_diff"+i+""));
            EditText L_length = findViewById(ids.get("L_length"+i+""));

            line.setLid(Lid.getText().toString());
            LPointClass startPoint = new LPointClass();
            startPoint.setPid(start_point.getText().toString());
            line.setSPid(startPoint);

            LPointClass endPoint = new LPointClass();
            endPoint.setPid(end_point.getText().toString());
            line.setEPid(endPoint);

            line.setDh(Double.parseDouble(H_diff.getText().toString()));
            line.setDistance(Double.parseDouble(L_length.getText().toString()));
            CurrentSegments.add(line);
        }

        for(LineClass TLine:CurrentSegments){

            //添加当前边起始点
            int Index_P = Belong(CurrentPoints, TLine.sPid.pid);
            int Index = 0;
            if (Index_P == -1)
            {
                //不在当前点集合中
                Index = Belong(ControlPoints, TLine.sPid.pid);
                if (Index == -1)
                {
                    //不是控制点
                    LPointClass lPointClass = new LPointClass();
                    lPointClass.setPid(TLine.sPid.pid);
                    lPointClass.setH(10000.00);
                    lPointClass.setControlP(false);
                    lPointClass.setH0(false);

                    CurrentPoints.add(lPointClass);

                    TLine.sPid.h = 10000.00;
                    TLine.sPid.isControlP = false;
                    TLine.sPid.isH0 = false;
                }
                else
                {
                    //是控制点
                    CurrentPoints.add(ControlPoints.get(Index));
                    TLine.sPid = ControlPoints.get(Index);
                }
            }
            //添加当前边尾点
            Index_P = Belong(CurrentPoints, TLine.ePid.pid);
            if (Index_P == -1)
            {
                //不在当前点集合中
                Index = Belong(ControlPoints, TLine.ePid.pid);
                if (Index == -1)
                {
                    //不是控制点

                    LPointClass lPointClass = new LPointClass();
                    lPointClass.setPid(TLine.ePid.pid);
                    lPointClass.setH(10000.00);
                    lPointClass.setControlP(false);
                    lPointClass.setH0(false);

                    CurrentPoints.add(lPointClass);

                    TLine.ePid.h = 10000.00;
                    TLine.ePid.isControlP = false;
                    TLine.ePid.isH0 = false;
                }
                else
                {
                    //是控制点
                    CurrentPoints.add(ControlPoints.get(Index));
                    TLine.ePid = ControlPoints.get(Index);
                }
            }

        }

        //发送数据
        Map<String,Object> param=new HashMap<>();
        param.put("taskName",taskName);
        param.put("ControlPoints",ControlPoints);
        param.put("CurrentSegments",CurrentSegments);
        param.put("CurrentPoints",CurrentPoints);
        Gson gson=new Gson();
        String s = gson.toJson(param);
        //返回结果

        HttpUtil.postJson(SysConstants.SmhUrl.POST_SENDDATA_URL, new HttpCallbackAdapter() {
            @Override
            public void onEntityFinish(String responseMsg, String dataJson) {
                showToast(responseMsg);
                result=MsgConverter.parseData(dataJson,new TypeToken<CalculateResult>(){}.getType());
                System.out.println("===================="+result.getDerta());
            }
        },s);
    }


    private static int Belong(List<LPointClass> CurrentPoints, String s)
    {
        for (LPointClass TPoint : CurrentPoints)
        {
            if (TPoint.pid .equals(s)) {
                return CurrentPoints.indexOf(TPoint);
            }
        }
            return -1;
    }

    public void addPointRow(View v){
        LinearLayout container = (LinearLayout) findViewById(R.id.point_table);
        LayoutInflater inflater = this.getLayoutInflater();
        TableRow view = (TableRow) inflater.inflate(R.layout.point_row, null);
        container.addView(changePointId(view));
        pointAddCount++;
    }

    public void addLineRow(View v){
        LinearLayout container = (LinearLayout) findViewById(R.id.line_table);
        LayoutInflater inflater = this.getLayoutInflater();
        TableRow view = (TableRow) inflater.inflate(R.layout.line_row, null);
        container.addView(changeLineId(view));
        lineAddCount++;
    }

    private View changeLineId(TableRow view) {
        EditText Lid = view.findViewById(R.id.Lid);
        EditText start_point = view.findViewById(R.id.start_point);
        EditText end_point = view.findViewById(R.id.end_point);
        EditText H_diff = view.findViewById(R.id.H_diff);
        EditText L_length = view.findViewById(R.id.L_length);


        Lid.setId(generateId("Lid"+lineAddCount+""));
        start_point.setId(generateId("start_point"+lineAddCount+""));
        end_point.setId(generateId("end_point"+lineAddCount+""));
        H_diff.setId(generateId("H_diff"+lineAddCount+""));
        L_length.setId(generateId("L_length"+lineAddCount+""));
        return view;
    }


    public  TableRow changePointId(TableRow view){
        EditText Pid = view.findViewById(R.id.Pid);
        EditText PH = view.findViewById(R.id.PH);
        EditText P_Know = view.findViewById(R.id.P_Know);
        EditText P_Un_Konw = view.findViewById(R.id.P_Un_Konw);

        Pid.setId(generateId("Pid"+pointAddCount+""));
        PH.setId(generateId("PH"+pointAddCount+""));
        P_Know.setId(generateId("P_Know"+pointAddCount+""));
        P_Un_Konw.setId(generateId("P_Un_Konw"+pointAddCount+""));
        return view;
    }

    public int generateId(String key){
        int id = generateViewId();
        ids.put(key,id);
        return id;
    }

    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TaskInfoActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
