package com.coship.validation.type.specific;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.coship.validation.constant.ErrorCodeEnum;
import com.coship.validation.exception.ValidationException;
import com.coship.validation.type.AbstractValidation;

public class RegexMatchValidation extends AbstractValidation{
	String regex;
	
	String fieldName;
	
	
	public RegexMatchValidation() {
		
	}
	
	public RegexMatchValidation(String regex, String code) {
		this.regex = regex;
		this.setCode(code);
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	@Override
	public boolean handler(Object field) {
		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher((String)field);
			// 字符串是否与正则表达式相匹配
			return matcher.matches();
		} catch (Exception e) {
			throw new ValidationException(ErrorCodeEnum.FieldTypeError.getCode(),ErrorCodeEnum.FieldTypeError.getMsg());
		}
	}

}
