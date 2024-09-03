package com.shanzhu.em.utils;


import java.io.Serializable;

public class ResultData<T> implements Serializable {

    private String resultCode;

    private String message;

    private T data;

    private boolean success;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static <T> ResultData<T> genSuccess(T data) {
        ResultData<T> result = new ResultData<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> ResultData<T> genError(String errorCode, String errorMessage) {
        ResultData<T> result = new ResultData<>();
        result.setSuccess(false);
        result.setResultCode(errorCode);
        result.setMessage(errorMessage);
        return result;
    }

    public static <T> ResultData<T> genException(String errorCode, String errorMessage) {
        ResultData<T> result = new ResultData<>();
        result.setSuccess(false);
        result.setResultCode(errorCode);
        result.setMessage(errorMessage);
        return result;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "resultCode='" + resultCode + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", success=" + success +
                '}';
    }
}
