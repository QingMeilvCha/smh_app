package com.lwz.login_demo.util;
/**
 * Created by zhouyuhang on 2018/12/24.
 * 消息实体类
 */

public class AdusResponse {

    private String code;
    private String msg;
    private Object data;

    public AdusResponse(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}