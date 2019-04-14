package com.lwz.login_demo.entity.shuizhun;

import lombok.Data;

/**
 * 边要素类
 * Created by zhouyuhang on 2019/4/11.
 */
@Data
public class LineClass {
    public String LID;            //观测边号
    public LPointClass SP;        //起点
    public LPointClass EP;        //终点
    public double dH;             //观测高差
    public double Distance;       //观测距离
}
