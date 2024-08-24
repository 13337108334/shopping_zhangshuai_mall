package com.shanzhu.em.service.util;

/**
 * 技术异常
 * 
 * @author viking.jinw
 */
public class TechException extends RuntimeException {

	private static final long serialVersionUID = -238574834802327692L;

	private String errCode;

	private String errMsg;

	/**
	 * 
	 * @param errCode
	 */
	public TechException(String errCode, String errMsg) {
		super(errCode);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	/**
	 * 
	 * @param errCode
	 */
	public TechException(ErrorCodeEnum errCode) {
		super(errCode.getErrorCode());
		this.errCode = errCode.getErrorCode();
	}

	public TechException(String errMsg, Throwable t) {
		super(ErrorCodeEnum.UNKNOWN.getErrorCode(), t);
		this.errCode = ErrorCodeEnum.UNKNOWN.getErrorCode();
		this.errMsg = errMsg;
	}

	public TechException(String errorCode, String errorMsg, Throwable t){
	    super(errorMsg, t);
	    this.errCode = errorCode;
	    this.errMsg = errorMsg;
    }

    public String getErrCode() {
		return this.errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

}
