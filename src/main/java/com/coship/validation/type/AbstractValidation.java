package com.coship.validation.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.coship.validation.Validator;
import com.coship.validation.constant.ErrorCodeEnum;
import com.coship.validation.exception.ValidationException;
import com.coship.validation.type.specific.RegexMatchValidation;

/**
 * 校验抽象类
 * 
 * @author 910190
 *
 */
public abstract class AbstractValidation {

	public static List<Validator> validatorList = new ArrayList<Validator>();

	public static Map<String, String> errorMap = new HashMap<String, String>();
	// public static Map<String, String> resultType = new HashMap<String, String>();

	/** 校验不通过返回对象类型 */
	public static String returnType;
	/** 校验不通过返回错误code字段名 */
	public static String returnCode;
	/** 校验不通过返回对象类型 */
	public static String returnDesc;
	// 默认错误码
	protected String code;

	public void validate(Object field) {
		if (!handler(field)) {
			throw new ValidationException(StringUtils.isBlank(code) ? ErrorCodeEnum.ValdateError.getCode() : code,
					StringUtils.isBlank(AbstractValidation.errorMap.get(code)) ? ErrorCodeEnum.ValdateError.getMsg()
							: AbstractValidation.errorMap.get(code));
		}
	}

	public abstract boolean handler(Object field);

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
