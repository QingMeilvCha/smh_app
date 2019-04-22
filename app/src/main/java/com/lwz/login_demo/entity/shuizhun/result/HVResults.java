package com.lwz.login_demo.entity.shuizhun.result;

import lombok.Data;


/**
 * 高程初始值计算结果
 * Created by zhouyuhang on 2019/4/12.
 */
@Data
public class HVResults {
    public String hvResultId;
    public String pid;
    public double hv;
    //是否是控制点
    public boolean isControlP;
    //是否有初始高程
    public boolean isH0;
    public Integer mark;
}
