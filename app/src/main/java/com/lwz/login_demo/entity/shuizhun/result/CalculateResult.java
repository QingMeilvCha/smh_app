package com.lwz.login_demo.entity.shuizhun.result;

import lombok.Data;

import java.util.List;

/**
 * 计算结果实体类
 * Created by zhouyuhang on 2019/4/12.
 */
@Data
public class CalculateResult {
    public String calculateID;
    public List<HVResults> HVResults;
    public List<ALResults> ALResults;
    public double derta;//单位权中误差
}
