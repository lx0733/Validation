package com.coship.validation;

import java.util.HashMap;
import java.util.Map;

import com.coship.validation.type.AbstractValidation;


/**
 * 
 * @author 910190
 *	<validClass>标签的bean类
 */
public class Validator {
	private String validId;		//校验ID
	private String validClass;	 //需要被校验的类
	private String validGroup;	 //校验类的组
	
	/**
	 * 校验规则集合
	 */
	private Map<String, AbstractValidation> validMap = new HashMap<String, AbstractValidation>();

	public String getValidClass() {
		return validClass;
	}

	public void setValidClass(String validClass) {
		this.validClass = validClass;
	}

	public String getValidGroup() {
		return validGroup;
	}

	public void setValidGroup(String validGroup) {
		this.validGroup = validGroup;
	}

	public Map<String, AbstractValidation> getValidMap() {
		return validMap;
	}

	public void setValidMap(Map<String, AbstractValidation> validMap) {
		this.validMap = validMap;
	}

	public String getValidId() {
		return validId;
	}

	public void setValidId(String validId) {
		this.validId = validId;
	}

	
}
