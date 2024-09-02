package com.shanzhu.em.utils;

/**
 * 简化代码快速结果返回
 *
 * @author wzj
 * @date 2020/11/30
 */
public class ResultDataGenerator {
    public static <T> ResultData<T> genSuccess(T data) {
        ResultData<T> result = new ResultData<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> ResultData<T> genError(String errCode, String errMessage) {
        ResultData<T> result = new ResultData<>();
        result.setSuccess(false);
        result.setResultCode(errCode);
        result.setMessage(errMessage);
        return result;
    }

    public static <T> ResultData<T> genError(ErrorCodeAndMessage errorCodeAndMessage, Object... params) {
        ResultData<T> result = new ResultData<>();
        result.setSuccess(false);
        result.setResultCode(String.valueOf(errorCodeAndMessage.getErrorCode()));
        result.setMessage(errorCodeAndMessage.getErrorMessage());
        return result;
    }

    public static  <T> ResultData<T> build(ErrorCodeAndMessage errorCodeAndMessage, T data) {
        ResultData<T> resultData = new ResultData<T>();
        if (errorCodeAndMessage != null) {
            return genError(String.valueOf(errorCodeAndMessage.getErrorCode()), errorCodeAndMessage.getErrorMessage());
        }

        if (data != null) {
            return genSuccess(data);
        }
        return resultData;
    }

}
