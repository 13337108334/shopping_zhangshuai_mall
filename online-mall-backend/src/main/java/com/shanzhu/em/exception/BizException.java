package com.shanzhu.em.exception;

/**
 * 业务异常
 * 在业务运行中，抛出的异常错误
 *
 * @author: ZhangDaYe
 * @date: 2024-08-24
 */
public class BizException extends RuntimeException {

    /**
     * 错误码
     */
    private final String code;

    public BizException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
