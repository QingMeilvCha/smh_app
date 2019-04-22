package com.lwz.login_demo.entity.shuizhun;

import lombok.Data;

/**
 * 边要素类
 * Created by zhouyuhang on 2019/4/11.
 */
@Data
public class LineClass {
    public String lineDataId;
    public String lid;            //观测边号
    public LPointClass sPid;        //起点
    public LPointClass ePid;        //终点
    public double dh;             //观测高差
    public double distance;       //观测距离
    public Integer mark;
}
