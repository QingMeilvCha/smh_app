package com.lwz.login_demo.util;


/**
 * Created by zhouyuhang on 2018/12/24.
 */
public class SysConstants {

    /**
     * 接口返回值状态码
     */
    public static class ResponseCode {
        // 操作成功状态码
        public static final String SUCCESS = "0";
        // 操作失败状态码
        public static final String FAIL = "-1";
        // 检验异常状态码
        public static final String EXCEPTION = "-2";
    }

    /**
     * 测量类别
     */
    public static class SmType{
        public static final String SHUIZHUN="水准测量";
        public static final String DAOXAIN="导线测量";
    }

    /**
     * K值
     */
    public static class KValue{
        public static final Integer k1=4787;
        public static final Integer k2=4687;
    }
}
