package com.ly.learn01.api;

import com.ly.learn01.constant.ErrorCode;
import org.jetbrains.annotations.NotNull;

public class CommResult<T> {
    private long code;
    private String message;
    private T data;

    public CommResult() {
    }

    public CommResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommResult<T> suc(T data) {
        return new CommResult<T>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
    }

    public static <T> CommResult<T> suc(T data, String message) {
        return new CommResult<T>(ErrorCode.SUCCESS.getCode(), message, data);
    }

    public static <T> CommResult<T> fail(T data, String message) {
        return new CommResult<T>(ErrorCode.REQUEST_ERROR.getCode(), ErrorCode.SUCCESS.getMessage(), data);
    }

    public static <T> CommResult<T> fail(ErrorCode code) {
        return new CommResult<T>(code.getCode(), code.getMessage(), null);
    }

    public static <T> CommResult<T> fail(T data) {
        return new CommResult<T>(ErrorCode.REQUEST_ERROR.getCode(), ErrorCode.REQUEST_ERROR.getMessage(), data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
