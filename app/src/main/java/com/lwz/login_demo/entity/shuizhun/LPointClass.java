package com.lwz.login_demo.entity.shuizhun;

import lombok.Data;
/**
 * 点要素类
 * Created by zhouyuhang on 2019/4/11.
 */
@Data
public class LPointClass {
    public String PID;
    public double X;
    public double Y;
    public double H;
    //是否是控制点
    public boolean IsControlP;
    //是否有初始高程
    public boolean IsH0;
    //是否是公共点
    public boolean IsCommonP;
}
