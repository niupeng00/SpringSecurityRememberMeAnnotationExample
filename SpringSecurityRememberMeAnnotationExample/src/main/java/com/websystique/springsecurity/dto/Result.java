package com.websystique.springsecurity.dto;

import java.io.Serializable;

public class Result implements Serializable {

    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;

    private int status;
    private String message;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success() {
        return Result.createResult(STATUS_SUCCESS, "成功", null);
    }

    public static Result success(String message) {
        return Result.createResult(STATUS_SUCCESS, message, null);
    }

    public static Result success(String message, Object data) {
        return Result.createResult(STATUS_SUCCESS, message, data);
    }

    public static Result success(int status, String message) {
        return Result.createResult(status, message, null);
    }

    public static Result fail() {
        return Result.createResult(STATUS_FAIL, "失败", null);
    }

    public static Result fail(String message) {
        return Result.createResult(STATUS_FAIL, message, null);
    }

    public static Result fail(String message, Object data) {
        return Result.createResult(STATUS_FAIL, message, data);
    }

    public static Result fail(int status, String message) {
        return Result.createResult(status, message, null);
    }

    /**
     * 违反重构原则
     */
    private static Result createResult(int status, String message, Object data) {
        Result result = new Result();
        result.setStatus(status);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
