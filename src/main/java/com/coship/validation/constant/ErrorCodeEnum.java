package com.coship.validation.constant;

/**
 * 返回code
 * @author 910190
 *
 */
public enum ErrorCodeEnum {
	
	/**
	 * 校验不通过
	 */
	ValdateError("000","校验不通过"),
    /**
     * 校验异常
     */
    CommonError("001","校验发生异常"),
    /**
     * 参数类型异常
     */
    FieldTypeError("002","参数类型错误"),
    ;
    private String code;
    private String msg;
    
    
	private ErrorCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

   
}
