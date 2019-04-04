package com.coship.validation.type.specific;


import org.apache.commons.lang3.StringUtils;

import com.coship.validation.type.AbstractValidation;

public class NotNullValidation extends AbstractValidation {

	String fieldName;
	
	public NotNullValidation() {
		
	}
	
	public NotNullValidation(String code) {
		this.setCode(code);
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	@Override
	public boolean handler(Object field) {
		if (null == field || StringUtils.isBlank(String.valueOf(field))) {
			return false;
		}
		return true;
	}

}
