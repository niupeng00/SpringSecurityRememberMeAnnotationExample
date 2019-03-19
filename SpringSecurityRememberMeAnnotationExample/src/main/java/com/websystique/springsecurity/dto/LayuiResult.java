package com.websystique.springsecurity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LayuiResult implements Serializable {


    private static final int CODE = 0;
    private static final String message = "成功";

    private int code;
    private String msg;
    private int count;
    private Object data;

    public static LayuiResult success(String message, int count, Object data) {
        return LayuiResult.createLayuiResult(CODE, message, count, data);
    }

    public static LayuiResult success(int count, Object data) {
        return LayuiResult.createLayuiResult(CODE, message, count, data);
    }

    private static LayuiResult createLayuiResult(int code, String message, int count, Object data) {
        LayuiResult layuiResult = new LayuiResult();
        layuiResult.setCode(code);
        layuiResult.setMsg(message);
        layuiResult.setCount(count);
        layuiResult.setData(data);
        return layuiResult;
    }
}
