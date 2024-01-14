package com.example.car_repair.util;

import lombok.Data;

@Data
public class Result {
    private boolean status; // 状态，是否成功
    private String msg; // 失败时返回的错误消息
    private Object data; // 成功时返回的结果

    public Result(boolean status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }


    public Result(Object data) {
        this.status = true;
        this.msg = "OK";
        this.data = data;
    }

    public boolean isSuccess() {
        return status;
    }

    public static Result errorMsg(String msg) {
        return new Result(false, msg, null);
    }

    public static Result ok(String msg) {
        return new Result(true, msg, null);
    }

    public static Result ok(Object obj) {
        return new Result(obj);
    }
}
