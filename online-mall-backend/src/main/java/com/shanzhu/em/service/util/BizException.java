package com.shanzhu.em.service.util;


/**
 * 业务异常
 * @author zhangshuai
 * @Date 2024/08/24
 */
public class BizException extends RuntimeException {


    private static final long serialVersionUID = -2600060042851653253L;
    /**
     * 错误码
     */
    private String errCode;
    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * @param errCode
     */
    public BizException(String errCode) {
        super(errCode);
        this.errCode = errCode;
    }

    /**
     * @param errCode
     * @param errMsg
     */
    public BizException(String errCode, String errMsg) {
        super(errCode);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BizException(String errCode, Throwable cause) {
        super(errCode, cause);
        this.errCode = errCode;
    }

    /**
     * @param errCode
     * @param errMsg
     * @param parent
     */
    public BizException(String errCode, String errMsg, Throwable parent) {
        super(errCode, parent);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public BizException(ErrorCodeEnum errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errCode = errorCode.getErrorCode();
        this.errMsg = errorMsg;
    }

    public BizException(ErrorCodeEnum errorCode, String errorMsg) {
        super(errorMsg);
        this.errCode = errorCode.getErrorCode();
        this.errMsg = errorMsg;
    }

    public BizException(ErrorCodeEnum errorCode) {
        super(errorCode.getErrorCode());
        this.errCode = errorCode.getErrorCode();
    }
}
