package com.lwz.login_demo.entity.shuizhun;

import lombok.Data;
/**
 * 点要素类
 * Created by zhouyuhang on 2019/4/11.
 */
@Data
public class LPointClass {
    public String pid;
    public String pointDataId;
    public double x;
    public double y;
    public double h;
    //是否是控制点
    public boolean isControlP;
    //是否有初始高程
    public boolean isH0;
    public Integer mark;
}
