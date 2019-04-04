package com.coship.validation.exception;

/**
 * 校验异常
 * @author 910190
 *
 */
public class ValidationException extends RuntimeException{
	private String errorCode;
	private String errorMsg;

	
    public ValidationException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public ValidationException() {
    }
    
    

    
}
