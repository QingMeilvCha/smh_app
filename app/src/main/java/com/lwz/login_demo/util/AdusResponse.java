package com.lwz.login_demo.util;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by zhouyuhang on 2018/12/24.
 * 消息实体类
 */
@Data
public class AdusResponse<T> implements Serializable {

    private String code;
    private String msg;
    private T data;
    private String dataJson;

    public AdusResponse(String code, String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public AdusResponse(String code, String msg, String dataJson) {
        this.code = code;
        this.msg = msg;
        this.dataJson=dataJson;
    }
}
