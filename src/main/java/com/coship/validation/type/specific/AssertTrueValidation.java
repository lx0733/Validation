package com.coship.validation.type.specific;

import com.coship.validation.type.AbstractValidation;

/**
 * 
 * @ClassName: AssertTrueValidation   
 * @Description: 仅为true参数校验   
 * @author: 910191
 * @date: 2019年4月3日 下午2:25:46   
 *
 */
public class AssertTrueValidation extends AbstractValidation {

	public AssertTrueValidation() {
		
	}
	
	public AssertTrueValidation(String code) {
		this.setCode(code);
	}
	
	@Override
	public boolean handler(Object fieldValue) {
		if (fieldValue instanceof Boolean && (Boolean) fieldValue) {
			return true;
		}else if (! (fieldValue instanceof Boolean)) {
			return false;
		}
		return false;
	}

	@Override
	public String toString() {
		return "AssertTrueValidation [toString()=" + super.toString() + "]";
	}
	
}
