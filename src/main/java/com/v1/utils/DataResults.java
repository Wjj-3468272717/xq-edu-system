package com.v1.utils;

public class DataResults<T> {
    private int code;
    private String msg;
    private T data;

    public DataResults() {
    }

    private DataResults(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static DataResults success(ResultCode resultCode) {
        DataResults resultCommon = new DataResults(resultCode.getCode(), resultCode.getMsg());
        return resultCommon;
    }

    public static DataResults success(ResultCode resultCode, Object data) {
        DataResults success = success(resultCode);
        success.setData(data);
        return success;
    }

    // ❌ 问题：fail方法错误地调用了success方法
    public static DataResults fail(ResultCode resultCode) {
        return success(resultCode);  // 这里应该返回失败，却返回了成功
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}